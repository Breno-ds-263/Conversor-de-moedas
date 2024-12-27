package com.ConversorDeMoedas.main;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.ConversorDeMoedas.models.Conversao;
import com.google.gson.Gson;

public class main {

	public static void main(String[] args) throws IOException, InterruptedException {

		Scanner leitura = new Scanner(System.in);
		String apikey = "800bd8714b7ad90336c95bc4";		
		HttpClient client = HttpClient.newHttpClient();
		
     	String moeda = "";
     	String moeda2= "";
     	
     	int opcao = 0;
	
     	while(opcao  != 7) {
     	System.out.println();
     	System.out.println();
     	System.out.println("Digite o valor que deseja converter");
     


     	String quantidade= leitura.nextLine();
		
		String menu = """
				1) Dólar => Peso argentino
				2) Peso argentino => Dólar
				3) Dólar => Real brasileiro
				4) Real brasileiro => Dólar
				5) Dólar => Peso colombiano
				6) Peso colombiano => Dólar
				7) Sair

				Escolha uma opção válida:
				""";
		
		System.out.println(menu);
	    opcao = leitura.nextInt();  
	    leitura.nextLine();

		switch(opcao){
			case 1:
				moeda = "USD";
				moeda2 = "ARS";
				break;
			case 2:
                moeda = "ARS";
                moeda2 = "USD";
                break;
            case 3:
                moeda = "USD";
                moeda2 = "BRL";
                break;
            case 4:
                moeda = "BRL";
                moeda2 = "USD";
                break;
            case 5:
                moeda = "USD";
                moeda2 = "COP";
                break;
            case 6:
                moeda = "COP";
                moeda2 = "USD";
                break;
            case 7:
                System.out.println("Saindo...");
                return; 
            default:
                System.out.println("Opção inválida.");
        }
		
		String endereco =  "https://v6.exchangerate-api.com/v6/" + apikey + "/pair/" + moeda+"/"+ moeda2 +"/"+ quantidade;
		
		HttpRequest request = HttpRequest.newBuilder()
			      .uri(URI.create(endereco))
			      .build();
		
		HttpResponse<String> response = client
				  .send(request, HttpResponse.BodyHandlers.ofString());
		
		String json = response.body();
		
		Gson gson = new Gson();
		
		Conversao conversao = gson.fromJson(json, Conversao.class);
		
		System.out.printf("Valor %s %s corresponde ao valor final de =>>> %s %s", quantidade, conversao.base_code(), conversao.conversion_result(), conversao.target_code());
		
     	}

			
		}
}

