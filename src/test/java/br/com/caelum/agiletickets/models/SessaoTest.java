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
	public void test() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		//espetaculo.criarSessoes(new LocalDate(), LocalDate fim, LocalTime horario, Periodicidade periodicidade);
		
		Assert.assertTrue(true);
	}
	
}
