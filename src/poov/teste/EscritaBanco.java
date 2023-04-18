package poov.teste;

import java.sql.*;
import java.util.Scanner;
import poov.Modelo.Vacina;

public class EscritaBanco {
    public static void main(String[] args) throws Exception {
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
            Vacina vacina = new Vacina();
            System.out.println("Digite o nome da vacina: ");
            vacina.setNome(scanner.nextLine());
            System.out.println("Descricao: ");
            vacina.setDescricao(scanner.nextLine());
            String query = "INSERT INTO vacina (nome, descricao) VALUES (?, ?)";
            PreparedStatement statement = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, vacina.getNome());
            statement.setString(2, vacina.getDescricao());
            if (statement.executeUpdate() == 1) {
                System.out.println("Vacina cadastrada com sucesso!");
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    vacina.setCodigo(resultSet.getLong(1));
                } else {
                    System.out.println("Erro ao cadastrar vacina!");
                }
                resultSet.close();
            }
            scanner.close();

            // ESCRITA NO BANCO
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
