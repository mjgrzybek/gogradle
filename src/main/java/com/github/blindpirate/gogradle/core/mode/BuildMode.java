package com.github.blindpirate.gogradle.core.mode;

import com.github.blindpirate.gogradle.core.dependency.GolangDependencySet;

import static com.github.blindpirate.gogradle.core.dependency.GolangDependencySet.merge;

public enum BuildMode {
    Develop {
        @Override
        public GolangDependencySet determine(GolangDependencySet declaredDependencies,
                                             GolangDependencySet vendorDependencies,
                                             GolangDependencySet lockedDependencies) {
            return merge(declaredDependencies, vendorDependencies, lockedDependencies);
        }
    },
    Reproducible {
        @Override
        public GolangDependencySet determine(GolangDependencySet declaredDependencies,
                                             GolangDependencySet vendorDependencies,
                                             GolangDependencySet lockedDependencies) {
            return merge(vendorDependencies, lockedDependencies, declaredDependencies);
        }
    };

    public abstract GolangDependencySet determine(
            GolangDependencySet declaredDependencies,
            GolangDependencySet vendorDependencies,
            GolangDependencySet lockedDependencies);
}
