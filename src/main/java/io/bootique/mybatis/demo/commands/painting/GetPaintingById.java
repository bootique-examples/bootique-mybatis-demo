package io.bootique.mybatis.demo.commands.painting;

import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.meta.application.CommandMetadata;
import io.bootique.mybatis.demo.model.Artist;
import io.bootique.mybatis.demo.model.Painting;
import io.bootique.mybatis.demo.model.mapper.ArtistMapper;
import io.bootique.mybatis.demo.model.mapper.PaintingMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

import javax.inject.Inject;
import javax.inject.Provider;

public class GetPaintingById extends CommandWithMetadata {

    private Provider<SqlSessionManager> sqlSessionManagerProvider;

    @Inject
    public GetPaintingById(Provider<SqlSessionManager> providerSessionManager) {
        super(CommandMetadata.builder("Get painting by id")
                .name("get-painting-by-id")
                .description("Get painting by id command. Required option \"--id\"")
                .build());
        this.sqlSessionManagerProvider = providerSessionManager;
    }

    @Override
    public CommandOutcome run(Cli cli) {
        int id;
        if (cli.hasOption("id")) {
            id = Integer.parseInt(cli.optionString("id"));
            try (SqlSession sqlSession = sqlSessionManagerProvider.get().openSession()) {
                PaintingMapper mapper = sqlSession.getMapper(PaintingMapper.class);
                Painting painting = mapper.getPaintingById(id);
                System.out.println(painting.toString());
            }
            return CommandOutcome.succeeded();
        } else {
            return CommandOutcome.failed(1, "please input \"--id\" ");
        }
    }
}