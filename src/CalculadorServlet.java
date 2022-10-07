import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calcular")
public class CalculadorServlet extends HttpServlet {
	
	private String idadePorExtenso(int dia, int mes, int ano) {
		LocalDate localDate = LocalDate.now();
		int diaAtual = localDate.getDayOfMonth();
		int mesAtual = localDate.getMonthValue();
		int anoAtual = localDate.getYear();
		int anos = anoAtual - ano;
		int meses = mesAtual - mes;
		int dias = diaAtual - dia;
		if(meses < 0) {
			anos -= 1;
			meses += 12;
		}
		if(dias < 0) {
			meses -= 1;
			dias += 30;
		}
		return String.format("%s anos %s meses %s dias", anos, meses, dias);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String nome = request.getParameter("nome");
		
		try {
			int dia = Integer.valueOf(request.getParameter("dia"));
			int mes = Integer.valueOf(request.getParameter("mes"));
			int ano = Integer.valueOf(request.getParameter("ano"));
			out.println("<html><body>");
			out.println("<h1>Olá, " + nome +"!<br/>Você tem " + idadePorExtenso(dia, mes, ano) + "</h1>");
			out.println("</body>>/html>");
		} catch (ArithmeticException exception) {
			out.println("“<html><body><h1>Erro na conversão de algum dos valores.</h1></body></html>");
		}
	
		out.close();

	 }
}