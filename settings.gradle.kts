pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Shop Yar"
include(":app")
include(":core")
include(":onboarding")
include(":onboarding:onboarding_presentation")
include(":onboarding:onboarding_domain")
include(":factor")
include(":factor:factor_data")
include(":factor:factor_domain")
include(":factor:factor_presentation")
include(":merchandise")
include(":merchandise:merchandise_data")
include(":merchandise:merchandise_domain")
include(":merchandise:merchandise_presentation")
include(":profile")
include(":profile:profile_data")
include(":profile:profile_domain")
include(":profile:profile_presentation")
include(":core_ui")
