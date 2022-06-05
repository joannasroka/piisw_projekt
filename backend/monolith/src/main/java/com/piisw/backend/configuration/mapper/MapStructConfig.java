package com.piisw.backend.configuration.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        implementationName = "<CLASS_NAME>Impl_"
)
public interface MapStructConfig {
}
