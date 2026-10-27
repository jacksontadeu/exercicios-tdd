package org.example;

import static org.example.triangulo.TipoTriangulo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.example.triangulo.Triangulo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrianguloTest {

    // ---------- Triângulos válidos ----------

    @Test
    @DisplayName("CT01 - Escaleno válido (3, 4, 5)")
    void ct01_escalenoValido() {
        assertEquals(ESCALENO, Triangulo.classificar(3, 4, 5));
    }

    @Test
    @DisplayName("CT02 - Isósceles válido (5, 5, 3)")
    void ct02_isoscelesValido() {
        assertEquals(ISOSCELES, Triangulo.classificar(5, 5, 3));
    }

    @Test
    @DisplayName("CT03 - Equilátero válido (3, 3, 3)")
    void ct03_equilateroValido() {
        assertEquals(EQUILATERO, Triangulo.classificar(3, 3, 3));
    }

    // ---------- Isósceles: permutações dos mesmos valores ----------

    @Test
    @DisplayName("CT04 - Isósceles permutação 1 (5, 5, 3)")
    void ct04_isoscelesPermutacao1() {
        assertEquals(ISOSCELES, Triangulo.classificar(5, 5, 3));
    }

    @Test
    @DisplayName("CT05 - Isósceles permutação 2 (5, 3, 5)")
    void ct05_isoscelesPermutacao2() {
        assertEquals(ISOSCELES, Triangulo.classificar(5, 3, 5));
    }

    @Test
    @DisplayName("CT06 - Isósceles permutação 3 (3, 5, 5)")
    void ct06_isoscelesPermutacao3() {
        assertEquals(ISOSCELES, Triangulo.classificar(3, 5, 5));
    }

    // ---------- Um valor zero ----------

    @Test
    @DisplayName("CT07 - Primeiro lado zero (0, 4, 5)")
    void ct07_primeiroLadoZero() {
        assertEquals(INVALIDO, Triangulo.classificar(0, 4, 5));
    }

    @Test
    @DisplayName("CT08 - Segundo lado zero (4, 0, 5)")
    void ct08_segundoLadoZero() {
        assertEquals(INVALIDO, Triangulo.classificar(4, 0, 5));
    }

    @Test
    @DisplayName("CT09 - Terceiro lado zero (4, 5, 0)")
    void ct09_terceiroLadoZero() {
        assertEquals(INVALIDO, Triangulo.classificar(4, 5, 0));
    }

    // ---------- Um valor negativo ----------

    @Test
    @DisplayName("CT10 - Primeiro lado negativo (-3, 4, 5)")
    void ct10_primeiroLadoNegativo() {
        assertEquals(INVALIDO, Triangulo.classificar(-3, 4, 5));
    }

    @Test
    @DisplayName("CT11 - Segundo lado negativo (3, -4, 5)")
    void ct11_segundoLadoNegativo() {
        assertEquals(INVALIDO, Triangulo.classificar(3, -4, 5));
    }

    @Test
    @DisplayName("CT12 - Terceiro lado negativo (3, 4, -5)")
    void ct12_terceiroLadoNegativo() {
        assertEquals(INVALIDO, Triangulo.classificar(3, 4, -5));
    }

    // ---------- Soma de 2 lados igual ao terceiro (1, 2, 3) - 6 permutações ----------

    @Test
    @DisplayName("CT13 - Soma igual ao terceiro (1, 2, 3)")
    void ct13_somaIgual_1_2_3() {
        assertEquals(INVALIDO, Triangulo.classificar(1, 2, 3));
    }

    @Test
    @DisplayName("CT14 - Soma igual ao terceiro (1, 3, 2)")
    void ct14_somaIgual_1_3_2() {
        assertEquals(INVALIDO, Triangulo.classificar(1, 3, 2));
    }

    @Test
    @DisplayName("CT15 - Soma igual ao terceiro (2, 1, 3)")
    void ct15_somaIgual_2_1_3() {
        assertEquals(INVALIDO, Triangulo.classificar(2, 1, 3));
    }

    @Test
    @DisplayName("CT16 - Soma igual ao terceiro (2, 3, 1)")
    void ct16_somaIgual_2_3_1() {
        assertEquals(INVALIDO, Triangulo.classificar(2, 3, 1));
    }

    @Test
    @DisplayName("CT17 - Soma igual ao terceiro (3, 1, 2)")
    void ct17_somaIgual_3_1_2() {
        assertEquals(INVALIDO, Triangulo.classificar(3, 1, 2));
    }

    @Test
    @DisplayName("CT18 - Soma igual ao terceiro (3, 2, 1)")
    void ct18_somaIgual_3_2_1() {
        assertEquals(INVALIDO, Triangulo.classificar(3, 2, 1));
    }

    // ---------- Soma de 2 lados menor que o terceiro (1, 2, 4) - 6 permutações ----------

    @Test
    @DisplayName("CT19 - Soma menor que o terceiro (1, 2, 4)")
    void ct19_somaMenor_1_2_4() {
        assertEquals(INVALIDO, Triangulo.classificar(1, 2, 4));
    }

    @Test
    @DisplayName("CT20 - Soma menor que o terceiro (1, 4, 2)")
    void ct20_somaMenor_1_4_2() {
        assertEquals(INVALIDO, Triangulo.classificar(1, 4, 2));
    }

    @Test
    @DisplayName("CT21 - Soma menor que o terceiro (2, 1, 4)")
    void ct21_somaMenor_2_1_4() {
        assertEquals(INVALIDO, Triangulo.classificar(2, 1, 4));
    }

    @Test
    @DisplayName("CT22 - Soma menor que o terceiro (2, 4, 1)")
    void ct22_somaMenor_2_4_1() {
        assertEquals(INVALIDO, Triangulo.classificar(2, 4, 1));
    }

    @Test
    @DisplayName("CT23 - Soma menor que o terceiro (4, 1, 2)")
    void ct23_somaMenor_4_1_2() {
        assertEquals(INVALIDO, Triangulo.classificar(4, 1, 2));
    }

    @Test
    @DisplayName("CT24 - Soma menor que o terceiro (4, 2, 1)")
    void ct24_somaMenor_4_2_1() {
        assertEquals(INVALIDO, Triangulo.classificar(4, 2, 1));
    }

    // ---------- Todos zero ----------

    @Test
    @DisplayName("CT25 - Três lados zero (0, 0, 0)")
    void ct25_tresLadosZero() {
        assertEquals(INVALIDO, Triangulo.classificar(0, 0, 0));
    }
}