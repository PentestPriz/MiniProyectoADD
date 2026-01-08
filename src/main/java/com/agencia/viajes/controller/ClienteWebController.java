package com.agencia.viajes.controller;

import com.agencia.viajes.model.Cliente;
import com.agencia.viajes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class ClienteWebController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public String inicio(Model model) {
        List<Cliente> clientes = clienteService.obtenerTodos();
        model.addAttribute("clientes", clientes);
        model.addAttribute("total", clientes.size());
        return "index";
    }
    
    @GetMapping("/nuevo")
    public String nuevoClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarCliente(@Valid @ModelAttribute("cliente") Cliente cliente, 
                                BindingResult result, 
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "formulario";
        }
        
        try {
            clienteService.crearCliente(cliente);
            redirectAttributes.addFlashAttribute("mensaje", "Cliente guardado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al guardar: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }
        
        return "redirect:/";
    }
    
    @GetMapping("/nacionalidad")
    public String buscarPorNacionalidad(@RequestParam(required = false) String nacionalidad, Model model) {
        if (nacionalidad != null && !nacionalidad.trim().isEmpty()) {
            List<Cliente> clientes = clienteService.buscarPorNacionalidad(nacionalidad);
            model.addAttribute("clientes", clientes);
            model.addAttribute("filtro", "Nacionalidad: " + nacionalidad);
            model.addAttribute("total", clientes.size());
        } else {
            return "redirect:/";
        }
        return "index";
    }
    
    @GetMapping("/con-seguro")
    public String clientesConSeguro(Model model) {
        List<Cliente> clientes = clienteService.clientesConSeguro();
        model.addAttribute("clientes", clientes);
        model.addAttribute("filtro", "Clientes con Seguro");
        model.addAttribute("total", clientes.size());
        return "index";
    }
    
    @GetMapping("/eliminar/{dni}")
    public String eliminarCliente(@PathVariable String dni, RedirectAttributes redirectAttributes) {
        try {
            boolean eliminado = clienteService.eliminarCliente(dni);
            if (eliminado) {
                redirectAttributes.addFlashAttribute("mensaje", "Cliente eliminado exitosamente");
                redirectAttributes.addFlashAttribute("tipoMensaje", "success");
            } else {
                redirectAttributes.addFlashAttribute("mensaje", "Cliente no encontrado");
                redirectAttributes.addFlashAttribute("tipoMensaje", "error");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al eliminar: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/";
    }
    
    @GetMapping("/editar/{dni}")
    public String editarClienteForm(@PathVariable String dni, Model model, RedirectAttributes redirectAttributes) {
        return clienteService.buscarPorDni(dni)
                .map(cliente -> {
                    model.addAttribute("cliente", cliente);
                    model.addAttribute("editando", true);
                    return "formulario";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("mensaje", "Cliente no encontrado");
                    redirectAttributes.addFlashAttribute("tipoMensaje", "error");
                    return "redirect:/";
                });
    }
}
