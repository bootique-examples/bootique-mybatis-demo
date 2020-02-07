package io.bootique.mybatis.demo.commands.painting;

import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.meta.application.CommandMetadata;
import io.bootique.mybatis.demo.model.Painting;
import io.bootique.mybatis.demo.model.mapper.PaintingMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.List;

public class GetAllPaintings extends CommandWithMetadata {


    private Provider<SqlSessionManager> sqlSessionManagerProvider;

    @Inject
    public GetAllPaintings(Provider<SqlSessionManager> providerSessionManager) {
        super(CommandMetadata.builder(GetAllPaintings.class).description("Get all paintings command").build());
        this.sqlSessionManagerProvider = providerSessionManager;
    }

    @Override
    public CommandOutcome run(Cli cli) {
        try (SqlSession sqlSession = sqlSessionManagerProvider.get().openSession()) {
            PaintingMapper mapper = sqlSession.getMapper(PaintingMapper.class);
            List<Painting> paintings = mapper.getAllPaintings();
            System.out.println(paintings.toString());
        }
        return CommandOutcome.succeeded();
    }
}
