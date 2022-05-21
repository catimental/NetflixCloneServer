package io.github.catimental.NetflixCloneServer.repository;

import io.github.catimental.NetflixCloneServer.domain.Member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository{

    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {

        final var sql = "insert into member(loginId, loginPassword) values(?, ?)";

        try (Connection connection = dataSource.getConnection();
             var ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, member.getLoginId());
            ps.setString(2, member.getLoginPassword());
            ps.executeUpdate();

            try (var rs = ps.getGeneratedKeys()) {
                if(rs.next()) {
                    member.setId(rs.getLong(1));
                } else {
                    throw new SQLException("id not found exception");
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        final var sql = "select * from member where id = ?";
        Member member = null;
        try (Connection connection = dataSource.getConnection();
             final var ps = connection.prepareStatement(sql);
         ) {
            ps.setLong(1, id);

            try (var rs = ps.executeQuery()) {
                if(rs.next()) {
                    member = new Member();
                    member.setId(rs.getLong("id"));
                    member.setLoginId(rs.getString("loginId"));
                    member.setLoginPassword(rs.getString("loginPassword"));
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByLoginId(String longinId) {
        final var sql = "select * from member where loginId = ?";
        Member member = null;
        try (Connection connection = dataSource.getConnection();
             final var ps = connection.prepareStatement(sql);
)        {
            ps.setString(1, longinId);

            try (final var rs = ps.executeQuery()) {
                if(rs.next()) {
                    member = new Member();
                    member.setId(rs.getLong("id"));
                    member.setLoginId(rs.getString("loginId"));
                    member.setLoginPassword(rs.getString("loginPassword"));
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll() {
        final var sql = "select * from member";
        var members = new ArrayList<Member>();
        try (Connection connection = dataSource.getConnection();
             final var ps = connection.prepareStatement(sql)) {

            try (final var rs = ps.executeQuery()) {
                while(rs.next()) {
                    var member = new Member();
                    member.setId(rs.getLong("id"));
                    member.setLoginId(rs.getString("loginId"));
                    member.setLoginPassword(rs.getString("loginPassword"));
                    members.add(member);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return members;
    }
}
