package br.com.exemplo.equipamentos.controller;

import br.com.exemplo.equipamentos.model.Equipamento;
import br.com.exemplo.equipamentos.model.EquipamentoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/equipamentos")
public class EquipamentoController {

  private final EquipamentoRepository repo;

  public EquipamentoController(EquipamentoRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public String listar(@RequestParam(value = "q", required = false) String q, Model model) {
    if (q != null && !q.isBlank()) {
      model.addAttribute("lista", repo.findByDescricaoContainingIgnoreCase(q));
      model.addAttribute("q", q);
    } else {
      model.addAttribute("lista", repo.findAll());
    }
    return "equipamentos/lista";
  }

  @GetMapping("/novo")
  public String novoForm(Model model) {
    model.addAttribute("equipamento", new Equipamento());
    return "equipamentos/form";
  }

  @PostMapping
  public String criar(@Valid @ModelAttribute("equipamento") Equipamento equipamento,
                      BindingResult br, RedirectAttributes ra, Model model) {
    if (br.hasErrors()) return "equipamentos/form";
    if (repo.findByDescricaoIgnoreCase(equipamento.getDescricao()).isPresent()) {
      model.addAttribute("erro", "Já existe equipamento com essa descrição");
      return "equipamentos/form";
    }
    repo.save(equipamento);
    ra.addFlashAttribute("msg_sucesso", "Equipamento criado com sucesso!");
    return "redirect:/equipamentos";
  }

  @GetMapping("/{id}/editar")
  public String editarForm(@PathVariable Long id, Model model) {
    Equipamento e = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado"));
    model.addAttribute("equipamento", e);
    return "equipamentos/form";
  }

  @PostMapping("/{id}")
  public String atualizar(@PathVariable Long id,
                          @Valid @ModelAttribute("equipamento") Equipamento dados,
                          BindingResult br, RedirectAttributes ra, Model model) {
    if (br.hasErrors()) return "equipamentos/form";
    Equipamento atual = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado"));

    if (!atual.getDescricao().equalsIgnoreCase(dados.getDescricao()) &&
        repo.findByDescricaoIgnoreCase(dados.getDescricao()).isPresent()) {
      model.addAttribute("erro", "Já existe equipamento com essa descrição");
      return "equipamentos/form";
    }

    atual.setDescricao(dados.getDescricao());
    atual.setAtivo(dados.getAtivo());
    repo.save(atual);
    ra.addFlashAttribute("msg_sucesso", "Equipamento atualizado com sucesso!");
    return "redirect:/equipamentos";
  }

  @PostMapping("/{id}/excluir")
  public String excluir(@PathVariable Long id, RedirectAttributes ra) {
    repo.deleteById(id);
    ra.addFlashAttribute("msg_sucesso", "Equipamento excluído.");
    return "redirect:/equipamentos";
  }

  @GetMapping("/{id}")
  public String detalhes(@PathVariable Long id, Model model) {
    Equipamento e = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado"));
    model.addAttribute("equipamento", e);
    return "equipamentos/detalhes";
  }
}
