package poov.teste;
import java.sql.*;
import java.util.Scanner;

public class LeituraBD {
    public static void main(String[] args) {
        String caminho = "jdbc:postgresql";
        String host = "localhost";
        String porta = "5433";
        String bd = "poov";
        String login = "postgres";
        String senha = "12345";
        String url = caminho + "://" + host + ":" + porta + "/" + bd;
        String classeDriver = "org.postgresql.Driver";
        Connection conexao = null;
        try {
            System.out.println("Conectando com o banco de dados.");
            Class.forName(classeDriver);
            conexao = DriverManager.getConnection(url, login, senha);
            System.out.println("Conexão com o banco de dados estabelecida.");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Codigo da vacina");
            int codigo = scanner.nextInt();
            String query = "SELECT * FROM vacina WHERE codigo = ?";
            PreparedStatement statement = conexao.prepareStatement(query);
            statement.setInt(1, codigo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Vacina encontrada!");
                System.out.println("Nome: " + resultSet.getString("nome"));
                System.out.println("Descricao: " + resultSet.getString("descricao"));
            } else {
                System.out.println("Vacina não encontrada!");
            }


            //LEITURA DO BANCO
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao carregar o driver JDBC.");
        } catch (SQLException ex) {
            System.out.println("Erro no acesso ao banco de dados.");
            SQLException e = ex;
            while (e != null) {
                System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Error Code: " + e.getErrorCode());
                System.out.println("Mensagem: " + e.getMessage());
                Throwable t = e.getCause();
                while (t != null) {
                    System.out.println("Causa: " + t);
                    t = t.getCause();
                }
                e = e.getNextException();
            }
        } finally {
            if (conexao != null) {
                System.out.println("Terminando a conexão com o banco de dados.");
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    System.out.println("Erro fechando a conexão com o banco de dados.");
                }
                System.out.println("Conexão com o banco de dados terminada.");
            }
        }


    }
    
}
