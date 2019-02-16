/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

public class Enlace 
 {
 	private String destino;
 	private double peso;
 
 	public Enlace(String desti, double peso1)
 	{
 		destino = desti;
 		peso = peso1;
 	}
 
 	public Enlace(String desti)
 	{
 		destino = desti;
 		peso = -1;
 	}
 
 	public void modificar(double peso1)
 	{
 		peso = peso1;
 	}
 
 	public String getDestino()
 	{
 		return destino;
 	}
 
 	public double getPeso()
 	{
 		return peso;
 	}
 }
