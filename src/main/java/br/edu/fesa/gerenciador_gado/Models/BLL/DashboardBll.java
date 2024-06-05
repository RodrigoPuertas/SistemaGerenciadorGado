/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Models.BLL;

import br.edu.fesa.gerenciador_gado.DAO.HistoricoPesosGadoDAO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author paulo
 */
public class DashboardBll {

    HistoricoPesosGadoDAO historicoPesosDAO;

    public String ReturnTotalAssets() {
        try {
            historicoPesosDAO = new HistoricoPesosGadoDAO();

            String url = "https://cepea.esalq.usp.br/br/widgetproduto.js.php?fonte=arial&tamanho=10&largura=400px&corfundo=dbd6b2&cortexto=333333&corlinha=ede7bf&id_indicador%5B%5D=2";

            Document doc = Jsoup.connect(url).ignoreContentType(true).get();

            String scriptContent = doc.body().text();

            Pattern pattern = Pattern.compile("document.write\\(`(.*?)`\\)", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(scriptContent);

            if (matcher.find()) {
                String textContent = matcher.group(1);

                textContent = textContent.replaceAll("<[^>]*>", "").trim();

                Pattern valuePattern = Pattern.compile("@\\s*R\\$\\s*(\\d+,\\d+)");
                Matcher valueMatcher = valuePattern.matcher(textContent);

                if (valueMatcher.find()) {
                    String valorTexto = valueMatcher.group(1);
                    valorTexto = valorTexto.replace(",", ".");
                    double valor = Double.parseDouble(valorTexto);
                    double valorArroba = 14.688;
                    double pesoTotal = historicoPesosDAO.GetTotalPoundFromCattle();
                    String valorTotal = String.format("%.2f", (pesoTotal / valorArroba) * valor);
                    return "R$ " + valorTotal;
                }
            }

            return "R$ 0,00";

        } catch (Exception ex) {
            return "R$ 0,00";
        }
    }

    public String valorArroba() {
        try {
            String url = "https://cepea.esalq.usp.br/br/widgetproduto.js.php?fonte=arial&tamanho=10&largura=400px&corfundo=dbd6b2&cortexto=333333&corlinha=ede7bf&id_indicador%5B%5D=2";

            Document doc = Jsoup.connect(url).ignoreContentType(true).get();

            String scriptContent = doc.body().text();

            Pattern pattern = Pattern.compile("document.write\\(`(.*?)`\\)", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(scriptContent);

            if (matcher.find()) {
                String textContent = matcher.group(1);

                textContent = textContent.replaceAll("<[^>]*>", "").trim();

                Pattern valuePattern = Pattern.compile("@\\s*R\\$\\s*(\\d+,\\d+)");
                Matcher valueMatcher = valuePattern.matcher(textContent);

                if (valueMatcher.find()) {
                    String valorTexto = valueMatcher.group(1);
                    valorTexto = valorTexto.replace(",", ".");
                    return valorTexto;
                }

            }

            return "Valor não encontrado";

        } catch (Exception ex) {
            return "Valor não encontrado";
        }
    }

}
