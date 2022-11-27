package model.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Item;
import model.entities.ItemSold;

public class Sales {
	
	private String path;
	
	List<ItemSold> list = new ArrayList<>();

	// Varre o arquivo .csv e adiciona os dados à lista
	public Sales(String path) {
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();

			while (line != null) {
				String[] datas = line.split(";");
				list.add(new ItemSold(new Item(datas[0], Double.parseDouble(datas[1])), Integer.parseInt(datas[2])));
				line = br.readLine();
			}

		} catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Error: " + e.getMessage());
		} catch(NumberFormatException e) {
			System.out.println("Error: Some data in the file was not placed correctly");
		}
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
	// Cria o arquivo summary.csv dentro de um diretório out
	public void generateSummary() {
		File outPath = new File(path);
		new File(outPath.getParent() + "\\out").mkdir(); // Cria o diretório

		// Escreve os dados da lista no arquivo summary
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(outPath.getParent() + "\\out\\summary.csv", true))) {
			for(ItemSold item : list) {
				bw.write(item.getItem().getName() + ";" + item.subTotal());
				bw.newLine();
			}
			
		} catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		for(ItemSold item : list) {
			sb.append("Product" + (list.indexOf(item) + 1));
			sb.append(" = [product_name=");
			sb.append(item.getItem().getName());
			sb.append(" unit_price=");
			sb.append(item.getItem().getPrice());
			sb.append(" sold_quantity=");
			sb.append(item.getQuantity());
			sb.append(" subtotal=");
			sb.append(item.subTotal());
			sb.append("]\n");
		}
		
		return sb.toString();
	}
}
