package hu.nive.ujratervezes.kepesitovizsga;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class Ladybird {

    private DataSource dataSource;

    public Ladybird(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> getLadybirdsWithExactNumberOfPoints(int potts) {
        List<String> result = new ArrayList<>();
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement("select hungarian_name, number_of_points from ladybirds where number_of_points = ?;");
        ) {
            stmt.setLong(1, potts);

            try (
                    ResultSet rs = stmt.executeQuery();
            ) {
                while (rs.next()) {
                    result.add(rs.getString("hungarian_name"));
                }
            } catch (SQLException sqle) {
                throw new IllegalArgumentException("Error by insert", sqle);
            }

        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Error by insert", sqle);
        }
        return result;
    }

    public Map<Integer, Integer> getLadybirdsByNumberOfPoints() {
        Map<Integer, Integer> result = new HashMap<>();

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement("select number_of_points from ladybirds;");
        ) {

            SelectedByPointsPreparedStatment(result, stmt);

        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Error by insert", sqle);
        }
        return result;
    }

    private void SelectedByPointsPreparedStatment(Map<Integer, Integer> result, PreparedStatement stmt) {
        try (
                ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                if (!result.keySet().contains(Integer.parseInt(rs.getString("number_of_points")))) {
                    result.put(Integer.parseInt(rs.getString("number_of_points")), 1);
                } else {
                    result.put(Integer.parseInt(rs.getString("number_of_points")), result.get(Integer.parseInt(rs.getString("number_of_points")))+1);
                }

            }
        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Error by insert", sqle);
        }
    }

    public Set<Ladybug> getLadybirdByPartOfLatinNameAndNumberOfPoints(String partOfName, int numberOfPoints) {
        Set<Ladybug> result = new HashSet<>();

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement("select hungarian_name, latin_name, genus, number_of_points from ladybirds where number_of_points = ?;");
        ) {
            stmt.setLong(1, numberOfPoints);

            try (
                    ResultSet rs = stmt.executeQuery();
            ) {
                while (rs.next()) {
                    if (rs.getString(2).contains(partOfName)) {
                        result.add(new Ladybug(rs.getString(1), rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)) ));
                    }
                }
            } catch (SQLException sqle) {
                throw new IllegalArgumentException("Error by insert", sqle);
            }

        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Error by insert", sqle);
        }
        return result;
    }

    public Map<String, Integer> getLadybirdStatistics() {

        Map<String, Integer> result = new HashMap<>();

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement("select genus from ladybirds;");
        ) {

            SelectedByGenusPreparedStatment(result, stmt);

        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Error by insert", sqle);
        }
        return result;
    }

    private void SelectedByGenusPreparedStatment(Map<String, Integer> result, PreparedStatement stmt) {
        try (
                ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                if (!result.keySet().contains(rs.getString("genus"))) {
                    result.put(rs.getString(1),1);
                } else {
                    result.put(rs.getString(1), result.get(rs.getString(1)) + 1);
                }

            }
        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Error by insert", sqle);
        }
    }
}
