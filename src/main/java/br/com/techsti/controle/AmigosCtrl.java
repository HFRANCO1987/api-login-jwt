package br.com.techsti.controle;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techsti.negocio.AmigosService;
import br.com.techsti.to.DadosBasicosTO;
import br.com.techsti.util.ResponseData;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 28/11/2018 - 17:28:41
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/amigos")
public class AmigosCtrl implements Serializable {

	private static final long serialVersionUID = 3156248100050399635L;

	@Autowired
	private AmigosService amigoService;

	@PutMapping(value = "/desfazer")
	public ResponseData desfazerAmizade(@RequestBody DadosBasicosTO dadosBasicos) {
		this.amigoService.desfazerAmizade(dadosBasicos.getAmigo());
		return new ResponseData(null, "Relacionamento de amizade desfeito!");
	}
	
	@GetMapping(value = "/{idconvidante}")
	public ResponseData desfazerAmizade(@PathVariable("idconvidante") Long id) {
		return new ResponseData(this.amigoService.findByPorIdConvidante(id));
	}

}
