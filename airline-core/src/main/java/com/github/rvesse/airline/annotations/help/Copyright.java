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
package com.github.rvesse.airline.annotations.help;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation that provides a copyright statement in a commands help
 *
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({TYPE})
@Documented
public @interface Copyright {

    /**
     * Gets/Sets the start year
     * @return Start year
     */
    int startYear();

    /**
     * Gets/Sets the end year
     * <p>
     * If less than the start year this is ignored
     * </p>
     *
     * @return End year
     */
    int endYear() default -1;

    /**
     * Gets/Sets the copyright holder
     *
     * @return Copyright holder
     */
    String holder();
}
