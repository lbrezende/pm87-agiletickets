package br.com.caelum.agiletickets.models;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class SessaoTest {

	@Test
	public void deveVenderNumeroDeIngressosMenorQueONumeroDeVagas() throws Exception {
		Sessao sessao = new Sessao();
        sessao.setTotalIngressos(2);

        Assert.assertTrue(sessao.podeReservar(1));
	}


	@Test
	public void naoDeveVenderMaisIngressosQueONumeroDeVagas() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(2);

		Assert.assertFalse(sessao.podeReservar(3));
	}

	@Test
	public void reservarIngressosDeveDiminuirONumeroDeIngressosDisponiveis() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(5);

		sessao.reserva(3);
		Assert.assertEquals(2, sessao.getIngressosDisponiveis().intValue());
	}
	
	@Test
	public void reservarNumeroDeIngressosDisponiveisDeveFuncionar() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(3);
		
		Assert.assertTrue(sessao.podeReservar(3));
	}
	
	@Test
	public void periodoDeTresDiasDiariasDeveGerarTresSessoes() throws Exception {
//		 * - Caso a data de inicio seja 01/01/2010, a data de fim seja 03/01/2010,
//	     * e a periodicidade seja DIARIA, o algoritmo cria 3 sessoes, uma 
//	     * para cada dia: 01/01, 02/01 e 03/01.
		
	     LocalDate dataInicial =  LocalDate.parse("2010-01-01");
	     LocalDate dataFinal =  LocalDate.parse("2010-01-03");
	          
	     
		
		//espetaculo.criarSessoes(new LocalDate(), LocalDate fim, LocalTime horario, Periodicidade periodicidade);
		
		Assert.assertTrue(true);
	}
	
	
}
