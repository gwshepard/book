package com.ea2sa.book;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.ea2sa.book");

        noClasses()
            .that()
                .resideInAnyPackage("com.ea2sa.book.service..")
            .or()
                .resideInAnyPackage("com.ea2sa.book.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.ea2sa.book.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
