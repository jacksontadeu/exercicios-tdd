package org.example.person;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
 
public class PersonDAO {
 
    public static final String ERRO_PESSOA_NULA = "Pessoa não informada";
    public static final String ERRO_NOME = "Nome deve ter ao menos 2 partes compostas apenas por letras";
    public static final String ERRO_IDADE = "Idade deve estar entre 1 e 200";
    public static final String ERRO_SEM_EMAIL = "Pessoa deve ter ao menos um email";
    public static final String ERRO_EMAIL_FORMATO = "Email deve estar no formato _____@____._____";
 
    private static final Pattern NOME = Pattern.compile("\\p{L}+(\\s+\\p{L}+)+");
    private static final Pattern EMAIL = Pattern.compile("[^@\\s]+@[^@\\s.]+\\.[^@\\s.]+");
 
    public void save(Person p) {
        // Fora do escopo do exercício (o foco é isValidToInclude).
        throw new UnsupportedOperationException("save ainda não implementado");
    }
 
    public List<String> isValidToInclude(Person p) {
        List<String> erros = new ArrayList<>();
 
        if (p == null) {
            erros.add(ERRO_PESSOA_NULA);
            return erros;
        }
 
        validarNome(p.getName(), erros);
        validarIdade(p.getAge(), erros);
        validarEmails(p.getEmails(), erros);
 
        return erros;
    }
 
    private void validarNome(String nome, List<String> erros) {
        if (nome == null || !NOME.matcher(nome.trim()).matches()) {
            erros.add(ERRO_NOME);
        }
    }
 
    private void validarIdade(int idade, List<String> erros) {
        if (idade < 1 || idade > 200) {
            erros.add(ERRO_IDADE);
        }
    }
 
    private void validarEmails(List<Email> emails, List<String> erros) {
        if (emails == null || emails.isEmpty()) {
            erros.add(ERRO_SEM_EMAIL);
            return;
        }
        for (Email email : emails) {
            String valor = email == null ? null : email.getName();
            if (valor == null || !EMAIL.matcher(valor).matches()) {
                erros.add(ERRO_EMAIL_FORMATO);
            }
        }
    }
}
 