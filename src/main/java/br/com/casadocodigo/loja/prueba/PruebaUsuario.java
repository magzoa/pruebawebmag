package br.com.casadocodigo.loja.prueba;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PruebaUsuario {
	
	public static void main(String[] args) {
        String senha = "123456";
        BCryptPasswordEncoder senhaBCrypt = new BCryptPasswordEncoder();
        System.out.println(senhaBCrypt.encode(senha));
        //$2a$10$nfI4z7eMVkYNfNK4InZXv.MpmN1Yeon8Ir16klHeTBIeA3GwUsD7S
    }

}
