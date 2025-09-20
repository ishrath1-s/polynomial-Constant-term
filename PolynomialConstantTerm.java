import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PolynomialConstantTerm {

    public static void main(String[] args) {
        String jsonInput = "{\n" +
                "  \"keys\": {\n" +
                "    \"n\": 4,\n" +
                "    \"k\": 3\n" +
                "  },\n" +
                "  \"1\": {\n" +
                "    \"base\": \"10\",\n" +
                "    \"value\": \"4\"\n" +
                "  },\n" +
                "  \"2\": {\n" +
                "    \"base\": \"2\",\n" +
                "    \"value\": \"111\"\n" +
                "  },\n" +
                "  \"3\": {\n" +
                "    \"base\": \"10\",\n" +
                "    \"value\": \"12\"\n" +
                "  },\n" +
                "  \"6\": {\n" +
                "    \"base\": \"4\",\n" +
                "    \"value\": \"213\"\n" +
                "  }\n" +
                "}";

        JSONObject obj = new JSONObject(jsonInput);

        int n = obj.getJSONObject("keys").getInt("n");
        int k = obj.getJSONObject("keys").getInt("k");

        int degree = k - 1;

        List<BigInteger> roots = new ArrayList<>();
        for (String key : obj.keySet()) {
            if (key.equals("keys")) continue;

            JSONObject rootObj = obj.getJSONObject(key);
            String baseStr = rootObj.getString("base");
            String valueStr = rootObj.getString("value");

            int base = Integer.parseInt(baseStr);

            BigInteger rootVal = new BigInteger(valueStr, base);
            roots.add(rootVal);
        }

        Collections.sort(roots);

        if (roots.size() < degree) {
            System.out.println("Not enough roots provided to determine the polynomial.");
            return;
        }

        BigInteger product = BigInteger.ONE;
        for (int i = 0; i < degree; i++) {
            product = product.multiply(roots.get(i));
        }

        BigInteger c = (degree % 2 == 0) ? product : product.negate();
        System.out.println(c.toString());
    }
}
