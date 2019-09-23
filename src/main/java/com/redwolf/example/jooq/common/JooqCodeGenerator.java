package com.redwolf.example.jooq.common;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class JooqCodeGenerator {

    public static void main(String[] args) throws Exception {

        Configuration configuration = new Configuration()
                .withJdbc(
                        new Jdbc()
                                .withDriver("com.mysql.cj.jdbc.Driver")
                                .withUrl("jdbc:mysql://localhost:3306")
                                .withUser("root")
                                .withPassword("root")
                )
                .withGenerator(
                        new Generator()
                                .withGenerate(
                                        new Generate()
                                                .withPojos(true)
                                                .withImmutablePojos(false)
                                                .withInterfaces(false)
                                                .withDaos(false)
                                                .withSpringAnnotations(true)
                                                .withJavaTimeTypes(true)
                                                .withDeprecated(false)
                                )
                                .withDatabase(
                                        new Database()
                                                .withName("org.jooq.meta.mysql.MySQLDatabase")
                                                .withSchemata(new Schema().withInputSchema("jooq_example"))
                                                .withSyntheticPrimaryKeys("public\\..*\\.id")
                                                .withOverridePrimaryKeys("override_primary_key")
                                                .withIncludes(".*")
                                                .withExcludes("device_0|device_1")
                                                .withForcedTypes(
                                                        new ForcedType()
                                                                .withName("BOOLEAN")
                                                                .withTypes("(?i:TINYINT(\\s*\\(\\d+\\))?(\\s*UNSIGNED)?)")
                                                )

                                )
                                .withTarget(
                                        new Target()
                                                .withPackageName("com.redwolf.example.jooq.generator")
                                                .withDirectory(System.getProperty("user.dir")+"/src/main/java")
                                )
                );
        GenerationTool.generate(configuration);

    }

}
