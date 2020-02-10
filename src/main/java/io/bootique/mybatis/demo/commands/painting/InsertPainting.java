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

public class InsertPainting extends CommandWithMetadata {

    private Provider<SqlSessionManager> sqlSessionManagerProvider;

    @Inject
    public InsertPainting(Provider<SqlSessionManager> providerSessionManager) {
        super(CommandMetadata.builder("Insert painting")
                .name("insert-painting")
                .description("Insert painting command. Required options \"--name\" and \"--artistId\" ").build());
        this.sqlSessionManagerProvider = providerSessionManager;
    }

    @Override
    public CommandOutcome run(Cli cli) {
        String name;
        int artistId;
        if (cli.hasOption("name") && cli.hasOption("artistId")){
            name = cli.optionString("name");
            artistId = Integer.parseInt(cli.optionString("artistId"));
            try (SqlSession sqlSession = sqlSessionManagerProvider.get().openSession()) {
                PaintingMapper mapper = sqlSession.getMapper(PaintingMapper.class);
                Painting painting = new Painting();
                painting.setName(name);
                painting.setArtistId(artistId);
                mapper.insertPainting(painting);
                sqlSession.commit();
                System.out.println(painting.toString() +  "Inserted");
            }
            return CommandOutcome.succeeded();
        } else {
            return CommandOutcome.failed(1, "please input \"--name\" and \"--artistId\" ");
        }

    }

}
