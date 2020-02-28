package br.com.techsti.email;

import br.com.techsti.entidade.Convite;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 12:53:23
 *
 */
public class EmailTemplate {

	/**
	 * 
	 * @author DF-DEV-2 henriquesantiagofranco@gmail.com
	 * @date 25/11/2018 - 14:28:19
	 *
	 *       RN: Prepara o e-mail esqueci senha
	 *
	 * @param esqueciSenha
	 * @return
	 */
	public static String prepararEmailNovaSenha(String email, String novaSenha) {
		StringBuffer emailEsqueciSenha = new StringBuffer();
		emailEsqueciSenha.append(" <!DOCTYPE html>                                                          ");
		emailEsqueciSenha.append(" <html lang='pt-br'>                                                      ");
		emailEsqueciSenha.append(" <head>                                                                   ");
		emailEsqueciSenha.append("    <meta charset='UTF-8'>                                                ");
		emailEsqueciSenha.append("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		emailEsqueciSenha.append("    <meta http-equiv='X-UA-Compatible' content='ie=edge'>                 ");
		emailEsqueciSenha.append("    <title>Rede de Amigos</title>                                         ");
		emailEsqueciSenha.append(" </head>                                                                  ");
		emailEsqueciSenha.append(" <body style='color: #979797;'>                                           ");
		emailEsqueciSenha.append("    <p>Olá,</p>                                                           ");
		emailEsqueciSenha.append("    <p>Esqueceu sua senha? Não se preocupe, foi gerado uma nova senha.</p>");
		emailEsqueciSenha.append("    <p>Seu usuário é:                                                     ");
		emailEsqueciSenha
				.append("        <span style='color: #4b84c1;'>" + email + " </span>                        ");
		emailEsqueciSenha.append("    </p>                                                                  ");
		emailEsqueciSenha.append("    <br>                                                                  ");
		emailEsqueciSenha.append("    <p>Sua nova senha é:</p>                                              ");
		emailEsqueciSenha.append("    <p>                                                                   ");
		emailEsqueciSenha.append("        <h3 style='color: #4b84c1;'>" + novaSenha                  + "</h3>");
		emailEsqueciSenha.append("    </p>                                                                  ");
		emailEsqueciSenha.append("    <br>                                                                  ");
		emailEsqueciSenha.append("    <br>                                                                  ");
		emailEsqueciSenha.append("    <br>                                                                  ");
		emailEsqueciSenha.append("    <p style='font-weight: bold;'>Equipe Rede de Amigos</p>               ");
		emailEsqueciSenha.append("</body>                                                                   ");
		emailEsqueciSenha.append("</html>                                                                   ");
		return emailEsqueciSenha.toString();
	}

	/**
	 * 
	 * @author DF-DEV-2 henriquesantiagofranco@gmail.com
	 * @date 25/11/2018 - 14:27:59
	 *
	 *       RN: Prepara o e-mail de convite
	 *
	 * @param convite
	 * @return
	 */
	public static String prepararEmailDoConvidadoComDadosDoConvite(Convite convite) {
		StringBuffer confirmaInscricao = new StringBuffer();
		confirmaInscricao.append(" <!DOCTYPE html>                                                                     ");
		confirmaInscricao.append(" <html lang='pt-br'>                                                                 ");
		confirmaInscricao.append(" <head>                                                                              ");
		confirmaInscricao.append("    <meta charset='UTF-8'>                                                           ");
		confirmaInscricao.append("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>           ");
		confirmaInscricao.append("    <meta http-equiv='X-UA-Compatible' content='ie=edge'>                            ");
		confirmaInscricao.append("    <title>Rede de Amigos</title>                                                    ");
		confirmaInscricao.append(" </head>                                                                             ");
		confirmaInscricao.append(" <body style='color: #979797;'>                                                      ");
		confirmaInscricao.append("    <p>Olá,</p>                                                                      ");
		confirmaInscricao.append(                                                                                   
				"    <p>" + convite.getConvidante().getNome() + " convida você para fazer parte de sua rede de amigos.</p>  ");
		confirmaInscricao.append(                                                                                      
				"    <p>Caso ainda não tenha cadastro, será necessário a realização do mesmo. Após o cadastro utilize o código do convite para aceitar fazer parte da rede.</p>:                    ");
		confirmaInscricao.append("    <br>                                                                             ");        
		confirmaInscricao.append("    <p>Código do convite para aceite de amizade:</p>                                 ");    
		confirmaInscricao.append("    <p>                                                                              ");        
		confirmaInscricao.append("        <h3 style='color: #4b84c1;'>" + convite.getInscricao() +              "</h3> ");
		confirmaInscricao.append("    </p>                                                                             ");
		confirmaInscricao.append("    <br>                                                                             ");
		confirmaInscricao.append("    <br>                                                                             ");
		confirmaInscricao.append("    <br>                                                                             ");
		confirmaInscricao.append("    <p style='font-weight: bold;'>Equipe Rede de Amigos</p>                          ");
		confirmaInscricao.append("</body>                                                                              ");
		confirmaInscricao.append("</html>                                                                              ");
		return confirmaInscricao.toString();                                                                         
	}

}
