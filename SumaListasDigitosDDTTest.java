package suma.listas.digitos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
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

	//Caso de dos listas
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

        BigInteger n1 = listaABigInteger(lista1);
        BigInteger n2 = listaABigInteger(lista2);
        BigInteger esperadoNum = n1.add(n2);

        List<Integer> esperado = bigIntegerALista(esperadoNum);

        System.out.println("-----");
        System.out.println("Lista1   : " + lista1);
        System.out.println("Lista2   : " + lista2);
        System.out.println("Esperado : " + esperado);
        System.out.println("Resultado: " + resultado);

        assertEquals(esperado, resultado, "La suma no coincide para " + lista1 + " + " + lista2);
    }

    
    //Caso Varias Listas
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

        BigInteger esperadoNum = BigInteger.ZERO;
        for (List<Integer> lista : listas) {
            esperadoNum = esperadoNum.add(listaABigInteger(lista));
        }
        List<Integer> esperado = bigIntegerALista(esperadoNum);

        System.out.println("-----");
        System.out.println("Listas   : " + listas);
        System.out.println("Esperado : " + esperado);
        System.out.println("Resultado: " + resultado);

        assertEquals(esperado, resultado);
    }

    //Funciones auxiliares
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

    static BigInteger listaABigInteger(List<Integer> lista) {
        StringBuilder sb = new StringBuilder();
        int i = 0, d = 0;

        for (i = 0; i < lista.size(); i++) {
            d = lista.get(i);
            sb.append(d);
        }

        return new BigInteger(sb.toString());
    }

    static List<Integer> bigIntegerALista(BigInteger n) {
        String s = n.toString(); 
        List<Integer> lista = new ArrayList<>();
        int i = 0;
        char c;

        for (i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            lista.add(Character.getNumericValue(c));	
        }

        return lista;
    }

}