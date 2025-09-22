package suma.listas.digitos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SumaListasDigitosDDTTest {

    static Stream<Arguments> casosSumaDosListas() throws Exception {
        Path path = Paths.get("src/test/resources/suma_dos_listas.txt");
        return Files.lines(path)
                .filter(line -> !line.isBlank() && !line.startsWith("#"))
                .map(line -> {
                    String[] partes = line.split(";");
                    List<Integer> lista1 = parseLista(partes[0]);
                    List<Integer> lista2 = parseLista(partes[1]);
                    return Arguments.of(lista1, lista2);
                });
    }

    @ParameterizedTest
    @MethodSource("casosSumaDosListas")
    void testSumaDosListas(List<Integer> lista1, List<Integer> lista2) {
        List<Integer> resultado = SumaListasDigitos.sumaDosListasDeDigitos(lista1, lista2);

        long n1 = listaAEntero(lista1);
        long n2 = listaAEntero(lista2);
        long esperadoNum = n1 + n2;

        List<Integer> esperado = enteroALista(esperadoNum);

        System.out.println("-----");
        System.out.println("Lista1   : " + lista1);
        System.out.println("Lista2   : " + lista2);
        System.out.println("Esperado : " + esperado);
        System.out.println("Resultado: " + resultado);

        assertEquals(esperado, resultado, "La suma no coincide para " + lista1 + " + " + lista2);
    }


    static Stream<Arguments> casosSumaVariasListas() throws Exception {
        Path path = Paths.get("src/test/resources/suma_varias_listas.txt");
        return Files.lines(path)
                .filter(line -> !line.isBlank() && !line.startsWith("#"))
                .map(line -> {
                    String[] partes = line.split(";");
                    List<List<Integer>> listas = new ArrayList<>();
                    for (String parte : partes) {
                        listas.add(parseLista(parte));
                    }
                    return Arguments.of(listas);
                });
    }

    @ParameterizedTest
    @MethodSource("casosSumaVariasListas")
    void testSumaVariasListas(List<List<Integer>> listas) {
        List<Integer> resultado = SumaListasDigitos.sumaVariasListasDeDigitos(listas);

        long esperadoNum = 0;
        for (List<Integer> lista : listas) {
            esperadoNum += listaAEntero(lista);
        }
        List<Integer> esperado = enteroALista(esperadoNum);

        System.out.println("-----");
        System.out.println("Listas   : " + listas);
        System.out.println("Esperado : " + esperado);
        System.out.println("Resultado: " + resultado);

        assertEquals(esperado, resultado);
    }

    // ======================
    // UTILIDADES
    // ======================
    
    
    
    static List<Integer> parseLista(String s) {
        s = s.replaceAll("[\\[\\]\\s]", "");
        List<Integer> resultado = new ArrayList<>();
        if (!s.isEmpty()) {
            for (String num : s.split(",")) {
                resultado.add(Integer.parseInt(num));
            }
        }
        return resultado;
    }

    static long listaAEntero(List<Integer> lista) {
        long res = 0;
        for (int d : lista) {
            res = res * 10 + d;
        }
        return res;
    }

    static List<Integer> enteroALista(long n) {
        if (n == 0) return List.of(0);
        List<Integer> lista = new ArrayList<>();
        while (n > 0) {
            lista.add(0, (int)(n % 10));
            n /= 10;
        }
        return lista;
    }
}
