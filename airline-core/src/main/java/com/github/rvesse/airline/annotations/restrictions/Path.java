/**
 * Copyright (C) 2010-16 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.rvesse.airline.annotations.restrictions;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation that marks that an options value must be a valid path to a
 * file/directory
 *
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ FIELD })
public @interface Path {

    /**
     * Gets/Sets whether the given file must exist
     * 
     * @return True if it must exist, false otherwise
     */
    boolean mustExist() default false;

    /**
     * Gets/Sets whether the given file must be writable
     * 
     * @return True if must be writable, false otherwise
     */
    boolean writable() default true;

    /**
     * Gets/Sets whether the given file must be readable
     * 
     * @return True if must be readable, false otherwise
     */
    boolean readable() default true;

    /**
     * Gets/Sets whether the given file must be executable
     * 
     * @return True if must be executable, false otherwise
     */
    boolean executable() default false;

    /**
     * Gets/Sets the kind of file that is expected
     * 
     * @return Expected file kind
     */
    PathKind kind() default PathKind.ANY;
}
