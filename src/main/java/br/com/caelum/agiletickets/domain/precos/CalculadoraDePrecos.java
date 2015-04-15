package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		

		Integer totalIngressos = sessao.getTotalIngressos();
		Integer ingressosReservados = sessao.getIngressosReservados();
		
		
		return calculaPrecoDaSessao(sessao, quantidade, totalIngressos, ingressosReservados);
	}

	private static BigDecimal calculaPrecoDaSessao(Sessao sessao, Integer quantidade, Integer totalIngressos, Integer ingressosReservados) {
		BigDecimal preco;
		
		TipoDeEspetaculo tipo = sessao.getEspetaculo().getTipo();
		BigDecimal precoPadraoDaSessao = sessao.getPreco();
		
		if(tipo.equals(TipoDeEspetaculo.CINEMA) || tipo.equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			if(taxaDeIngressosReservados(totalIngressos, ingressosReservados) <= 0.05) { 
				preco = precoPadraoDaSessao.add(precoPadraoDaSessao.multiply(BigDecimal.valueOf(0.10)));
			} else {
				preco = precoPadraoDaSessao;
			}
		} else if(tipo.equals(TipoDeEspetaculo.BALLET)) {
			if(taxaDeIngressosReservados(totalIngressos, ingressosReservados) <= 0.50) { 
				preco = precoPadraoDaSessao.add(precoPadraoDaSessao.multiply(BigDecimal.valueOf(0.20)));
			} else {
				preco = precoPadraoDaSessao;
			}
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(precoPadraoDaSessao.multiply(BigDecimal.valueOf(0.10)));
			}
		} else if(tipo.equals(TipoDeEspetaculo.ORQUESTRA)) {
			if(taxaDeIngressosReservados(totalIngressos, ingressosReservados) <= 0.50) { 
				preco = precoPadraoDaSessao.add(precoPadraoDaSessao.multiply(BigDecimal.valueOf(0.20)));
			} else {
				preco = precoPadraoDaSessao;
			}

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(precoPadraoDaSessao.multiply(BigDecimal.valueOf(0.10)));
			}
		}  else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = precoPadraoDaSessao;
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static double taxaDeIngressosReservados(Integer totalIngressos,	Integer ingressosReservados) {
		return (totalIngressos - ingressosReservados) / totalIngressos.doubleValue();
	}

}