package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		TipoDeEspetaculo tipo = sessao.getEspetaculo().getTipo();
		Integer totalIngressos = sessao.getTotalIngressos();
		Integer ingressosReservados = sessao.getIngressosReservados();
		BigDecimal precoPadraoDaSessao = sessao.getPreco();
		
		return calculaPrecoDaSessao(sessao, quantidade, tipo, totalIngressos,ingressosReservados, precoPadraoDaSessao);
	}

	private static BigDecimal calculaPrecoDaSessao(Sessao sessao, Integer quantidade, TipoDeEspetaculo tipo, Integer totalIngressos,
			Integer ingressosReservados, BigDecimal precoPadraoDaSessao) {
		BigDecimal preco;
		if(tipo.equals(TipoDeEspetaculo.CINEMA) || tipo.equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			if(calculaPorcentagemDeIngressosDisponiveis(totalIngressos, ingressosReservados) <= 0.05) { 
				preco = precoPadraoDaSessao.add(precoPadraoDaSessao.multiply(BigDecimal.valueOf(0.10)));
			} else {
				preco = precoPadraoDaSessao;
			}
		} else if(tipo.equals(TipoDeEspetaculo.BALLET)) {
			if(calculaPorcentagemDeIngressosDisponiveis(totalIngressos, ingressosReservados) <= 0.50) { 
				preco = precoPadraoDaSessao.add(precoPadraoDaSessao.multiply(BigDecimal.valueOf(0.20)));
			} else {
				preco = precoPadraoDaSessao;
			}
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(precoPadraoDaSessao.multiply(BigDecimal.valueOf(0.10)));
			}
		} else if(tipo.equals(TipoDeEspetaculo.ORQUESTRA)) {
			if(calculaPorcentagemDeIngressosDisponiveis(totalIngressos, ingressosReservados) <= 0.50) { 
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

	private static double calculaPorcentagemDeIngressosDisponiveis(Integer totalIngressos,
			Integer ingressosReservados) {
		return (totalIngressos - ingressosReservados) / totalIngressos.doubleValue();
	}

}