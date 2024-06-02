/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Models.BLL;

import br.edu.fesa.gerenciador_gado.DAO.CattleDAO;
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

    CattleDAO cattleDao = new CattleDAO();

    public void ReturnTotalAssets() {
        try {
            // URL do script que retorna o texto
            String url = "https://cepea.esalq.usp.br/br/widgetproduto.js.php?fonte=arial&tamanho=10&largura=400px&corfundo=dbd6b2&cortexto=333333&corlinha=ede7bf&id_indicador%5B%5D=2";

            // Faz a requisição e obtém o conteúdo do script
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();

            // Obtém o texto do script
            String scriptContent = doc.body().text();

            // Usa regex para extrair o conteúdo entre document.write(`...`)
            Pattern pattern = Pattern.compile("document.write\\(`(.*?)`\\)", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(scriptContent);

            if (matcher.find()) {
                String textContent = matcher.group(1);

                // Remove possíveis tags HTML e espaços extras
                textContent = textContent.replaceAll("<[^>]*>", "").trim();

                // Procura o símbolo "@" e pega o valor após ele
                Pattern valuePattern = Pattern.compile("@\\s*R\\$\\s*(\\d+,\\d+)");
                Matcher valueMatcher = valuePattern.matcher(textContent);

                if (valueMatcher.find()) {
                    String valorTexto = valueMatcher.group(1);
                    valorTexto = valorTexto.replace(",", ".");
                    double valor = Double.parseDouble(valorTexto);
                    double valorArroba = 14.688;
                    double pesoTotal = cattleDao.GetTotalPoundFromCattle();
                    String valorTotal = String.format("%.2f", (pesoTotal / valorArroba) * valor);
                    String patrimonioTotal = "Seu patrimônio no dia de hoje é: R$ " + valorTotal;
                } else {
                    System.out.println("Valor não encontrado.");
                }
            } else {
                System.out.println("Conteúdo não encontrado.");
            }
        } catch (Exception ex) {
            System.out.println("");
        }
    }
}
