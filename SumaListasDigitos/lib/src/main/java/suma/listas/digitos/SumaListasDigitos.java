package suma.listas.digitos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SumaListasDigitos {

    /**
     * Suma dos números representados como listas de dígitos (dms primero).
     * Dígitos permitidos: [0..9]. Devuelve dms primero.
     */
    public static List<Integer> sumaDosListasDeDigitos(List<Integer> lista1, List<Integer> lista2) {
        if (lista1 == null || lista2 == null) {
            throw new IllegalArgumentException("Las listas no pueden ser nulas");
        }
        validarDigitos(lista1);
        validarDigitos(lista2);

        int i = lista1.size() - 1;
        int j = lista2.size() - 1;
        int carry = 0;

        List<Integer> out = new ArrayList<>();
        while (i >= 0 || j >= 0 || carry > 0) {
            int d1 = i >= 0 ? lista1.get(i) : 0;
            int d2 = j >= 0 ? lista2.get(j) : 0;
            int sum = d1 + d2 + carry;

            out.add(sum % 10);
            carry = sum / 10;

            i--;
            j--;
        }
        Collections.reverse(out);
        return out;
    }

    /**
     * NUEVO: Suma n listas de dígitos (dms primero).
     * - Reutiliza la versión de dos listas de forma iterativa.
     * - Acepta cualquier número >= 1 de listas.
     */
    @SafeVarargs
    public static List<Integer> sumaVariasListasDeDigitos(List<Integer>... listas) {
        if (listas == null || listas.length == 0) {
            throw new IllegalArgumentException("Debe proporcionar al menos una lista");
        }
        // Validación temprana para mensajes de error más claros
        for (int idx = 0; idx < listas.length; idx++) {
            List<Integer> l = listas[idx];
            if (l == null) {
                throw new IllegalArgumentException("La lista en la posición " + idx + " es nula");
            }
            validarDigitos(l);
        }

        List<Integer> acumulado = List.of(0); // identidad aditiva
        for (List<Integer> l : listas) {
            acumulado = sumaDosListasDeDigitos(acumulado, l);
        }
        return normaliza(acumulado);
    }
    
    public static List<Integer> sumaVariasListasDeDigitos(Collection<? extends List<Integer>> listas) { 
    	@SuppressWarnings("unchecked")
		List<Integer>[] arrListas = listas.stream().toArray(List[]::new);
    	return sumaVariasListasDeDigitos(arrListas);
    }
    
    private static List<Integer> normaliza(List<Integer> out) {
    	  int i = 0;
    	  while (i < out.size() - 1 && out.get(i) == 0) i++;
    	  return i == 0 ? out : out.subList(i, out.size());
    }


    private static void validarDigitos(List<Integer> lista) {
        for (Integer d : lista) {
            if (d == null || d < 0 || d > 9) {
                throw new IllegalArgumentException("Sólo se permiten dígitos entre 0 y 9");
            }
        }
    }

    // Pequeño main de demo
    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(9, 9, 9);
        List<Integer> b = Arrays.asList(1);
        List<Integer> c = Arrays.asList(0, 0, 1, 2);   // 12
        List<Integer> d = Arrays.asList(4, 9);         // 49

        System.out.println("Suma de 1 lista: " + Arrays.asList(d) + " = " + sumaVariasListasDeDigitos(d));
        System.out.println("Suma de 2 listas: " + a + " + " + b + " = " + sumaDosListasDeDigitos(a, b));
        System.out.println("Suma de n listas: " + Arrays.asList(a, b, c, d) + " = " + sumaVariasListasDeDigitos(a, b, c, d));
        // Esperado: 999 + 1 + 12 + 49 = 1061 -> [1, 0, 6, 1]
    }
}