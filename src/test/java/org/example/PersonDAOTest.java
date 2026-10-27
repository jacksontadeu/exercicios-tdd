package org.example;

import static org.example.person.PersonDAO.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.example.person.Email;
import org.example.person.Person;
import org.example.person.PersonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PersonDAOTest {

    private PersonDAO dao;

    @BeforeEach
    void setUp() {
        dao = new PersonDAO();
    }

    // ---------- helpers ----------

    private Person pessoa(String nome, int idade, String... emails) {
        List<Email> lista = new java.util.ArrayList<>();
        for (int i = 0; i < emails.length; i++) {
            lista.add(new Email(i + 1, emails[i]));
        }
        return new Person(1, nome, idade, lista);
    }

    private Person pessoaValidaComNome(String nome) {
        return pessoa(nome, 30, "joao@email.com");
    }

    private Person pessoaValidaComIdade(int idade) {
        return pessoa("João Silva", idade, "joao@email.com");
    }

    private Person pessoaValidaComEmail(String email) {
        return pessoa("João Silva", 30, email);
    }

    // ---------- pessoa válida / nula ----------

    @Test
    @DisplayName("CT01 - Pessoa totalmente válida não retorna erros")
    void ct01_pessoaValida() {
        assertTrue(dao.isValidToInclude(pessoa("João Silva", 30, "joao@email.com")).isEmpty());
    }

    @Test
    @DisplayName("CT02 - Pessoa nula retorna erro de pessoa não informada")
    void ct02_pessoaNula() {
        assertEquals(List.of(ERRO_PESSOA_NULA), dao.isValidToInclude(null));
    }

    // ---------- nome ----------

    @Test
    @DisplayName("CT03 - Nome com duas partes é válido")
    void ct03_nomeDuasPartes() {
        assertTrue(dao.isValidToInclude(pessoaValidaComNome("Maria Souza")).isEmpty());
    }

    @Test
    @DisplayName("CT04 - Nome com três partes é válido")
    void ct04_nomeTresPartes() {
        assertTrue(dao.isValidToInclude(pessoaValidaComNome("Maria da Silva")).isEmpty());
    }

    @Test
    @DisplayName("CT05 - Nome com letras acentuadas é válido")
    void ct05_nomeAcentuado() {
        assertTrue(dao.isValidToInclude(pessoaValidaComNome("José Conceição")).isEmpty());
    }

    @ParameterizedTest(name = "CT06 - Nome nulo ou vazio [{0}] é inválido")
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void ct06_nomeNuloOuVazio(String nome) {
        assertEquals(List.of(ERRO_NOME), dao.isValidToInclude(pessoaValidaComNome(nome)));
    }

    @Test
    @DisplayName("CT07 - Nome com apenas uma parte é inválido")
    void ct07_nomeUmaParte() {
        assertEquals(List.of(ERRO_NOME), dao.isValidToInclude(pessoaValidaComNome("João")));
    }

    @ParameterizedTest(name = "CT08 - Nome com número/símbolo [{0}] é inválido")
    @ValueSource(strings = {"João Silva2", "J0ão Silva", "João Silva!", "João @Silva", "João 123"})
    void ct08_nomeComNaoLetras(String nome) {
        assertEquals(List.of(ERRO_NOME), dao.isValidToInclude(pessoaValidaComNome(nome)));
    }

    // ---------- idade ----------

    @Test
    @DisplayName("CT09 - Idade 1 (limite inferior) é válida")
    void ct09_idadeLimiteInferior() {
        assertTrue(dao.isValidToInclude(pessoaValidaComIdade(1)).isEmpty());
    }

    @Test
    @DisplayName("CT10 - Idade 200 (limite superior) é válida")
    void ct10_idadeLimiteSuperior() {
        assertTrue(dao.isValidToInclude(pessoaValidaComIdade(200)).isEmpty());
    }

    @Test
    @DisplayName("CT11 - Idade 0 é inválida")
    void ct11_idadeZero() {
        assertEquals(List.of(ERRO_IDADE), dao.isValidToInclude(pessoaValidaComIdade(0)));
    }

    @Test
    @DisplayName("CT12 - Idade negativa é inválida")
    void ct12_idadeNegativa() {
        assertEquals(List.of(ERRO_IDADE), dao.isValidToInclude(pessoaValidaComIdade(-5)));
    }

    @Test
    @DisplayName("CT13 - Idade 201 é inválida")
    void ct13_idadeAcimaDoLimite() {
        assertEquals(List.of(ERRO_IDADE), dao.isValidToInclude(pessoaValidaComIdade(201)));
    }

    // ---------- quantidade de emails ----------

    @Test
    @DisplayName("CT14 - Lista de emails nula é inválida")
    void ct14_emailsNulos() {
        Person p = new Person(1, "João Silva", 30, null);
        assertEquals(List.of(ERRO_SEM_EMAIL), dao.isValidToInclude(p));
    }

    @Test
    @DisplayName("CT15 - Lista de emails vazia é inválida")
    void ct15_emailsVazios() {
        Person p = new Person(1, "João Silva", 30, List.of());
        assertEquals(List.of(ERRO_SEM_EMAIL), dao.isValidToInclude(p));
    }

    @Test
    @DisplayName("CT16 - Mais de um email válido é aceito")
    void ct16_variosEmailsValidos() {
        assertTrue(dao.isValidToInclude(
                pessoa("João Silva", 30, "joao@email.com", "j@a.b")).isEmpty());
    }

    // ---------- formato do email ----------

    @Test
    @DisplayName("CT17 - Email com uma letra em cada parte (a@b.c) é válido")
    void ct17_emailMinimoValido() {
        assertTrue(dao.isValidToInclude(pessoaValidaComEmail("a@b.c")).isEmpty());
    }

    @ParameterizedTest(name = "CT18 - Email inválido [{0}]")
    @ValueSource(strings = {
            "joaoemail.com",      // sem @
            "joao@emailcom",      // sem ponto no domínio
            "@email.com",         // parte local vazia
            "joao@.com",          // domínio vazio
            "joao@email.",        // extensão vazia
            "joao@@email.com",    // dois @
            "jo ao@email.com",    // espaço
            "joao@email..com"     // domínio vazio entre pontos
    })
    void ct18_emailFormatoInvalido(String email) {
        assertEquals(List.of(ERRO_EMAIL_FORMATO), dao.isValidToInclude(pessoaValidaComEmail(email)));
    }

    @ParameterizedTest(name = "CT19 - Email nulo ou vazio [{0}] é inválido")
    @NullAndEmptySource
    void ct19_emailNuloOuVazio(String email) {
        assertEquals(List.of(ERRO_EMAIL_FORMATO), dao.isValidToInclude(pessoaValidaComEmail(email)));
    }

    @Test
    @DisplayName("CT20 - Um email válido e um inválido geram um erro de formato")
    void ct20_umValidoUmInvalido() {
        Person p = pessoa("João Silva", 30, "joao@email.com", "invalido");
        assertEquals(List.of(ERRO_EMAIL_FORMATO), dao.isValidToInclude(p));
    }

    // ---------- múltiplos erros ----------

    @Test
    @DisplayName("CT21 - Nome, idade e email inválidos acumulam três erros")
    void ct21_todosInvalidos() {
        Person p = pessoa("João", 0, "invalido");
        assertEquals(List.of(ERRO_NOME, ERRO_IDADE, ERRO_EMAIL_FORMATO), dao.isValidToInclude(p));
    }

    @Test
    @DisplayName("CT22 - Nome inválido e sem email acumulam dois erros")
    void ct22_nomeInvalidoESemEmail() {
        Person p = new Person(1, "João2 Silva", 30, List.of());
        assertEquals(List.of(ERRO_NOME, ERRO_SEM_EMAIL), dao.isValidToInclude(p));
    }
}