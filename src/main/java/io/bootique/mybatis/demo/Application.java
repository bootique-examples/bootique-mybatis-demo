package io.bootique.mybatis.demo;

import io.bootique.BQCoreModule;
import io.bootique.Bootique;
import io.bootique.di.BQModule;
import io.bootique.di.Binder;
import io.bootique.meta.application.OptionMetadata;
import io.bootique.mybatis.MybatisModule;
import io.bootique.mybatis.demo.commands.artist.GetArtistById;
import io.bootique.mybatis.demo.commands.artist.InsertArtist;
import io.bootique.mybatis.demo.commands.painting.GetAllPaintings;
import io.bootique.mybatis.demo.commands.painting.GetPaintingById;
import io.bootique.mybatis.demo.commands.painting.InsertPainting;
import io.bootique.mybatis.demo.model.mapper.ArtistMapper;
import io.bootique.mybatis.demo.commands.artist.GetAllArtists;
import io.bootique.mybatis.demo.model.mapper.PaintingMapper;

public class Application implements BQModule {

    public static void main(String[] args) {

        Bootique
                .app(args)
                .module(Application.class)
                .autoLoadModules()
                .exec()
                .exit();
    }

    private static OptionMetadata idOption() {
        return OptionMetadata.builder("id")
                .description("input id to select by id")
                .valueRequired("id")
                .build();
    }

    private static OptionMetadata nameOption() {
        return OptionMetadata.builder("name")
                .description("input name")
                .valueRequired("name")
                .build();
    }

    private static OptionMetadata artistIdOption() {
        return OptionMetadata.builder("artistId")
                .description("input artistId")
                .valueRequired("artistId")
                .build();
    }


    @Override
    public void configure(Binder binder) {
        MybatisModule.extend(binder)
                .addMapper(ArtistMapper.class)
                .addMapper(PaintingMapper.class);

        BQCoreModule.extend(binder)
                .addCommand(GetAllArtists.class)
                .addCommand(InsertArtist.class)
                .addOption(idOption())
                .addOption(nameOption())
                .addOption(artistIdOption())
                .addCommand(GetArtistById.class)
                .addCommand(GetAllPaintings.class)
                .addCommand(GetPaintingById.class)
                .addCommand(InsertPainting.class)
                .addConfig("classpath:config.yml");
    }
}
