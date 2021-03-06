/*
 * This file is part of the swblocks-decisiontree library.
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

package org.swblocks.decisiontree.tree;

import java.util.function.Predicate;

import org.swblocks.jbl.util.Range;

/**
 * Evaluation class for evaluating String inputs of Generic {link Range} inputs.
 */
public abstract class GenericRangeEvaluation<T extends Comparable<? super T>> implements Predicate<String> {
    private final Range<T> range;
    private final String name;

    GenericRangeEvaluation(final String name, final Range<T> range) {
        this.range = range;
        this.name = name;
    }

    @Override
    public boolean test(final String inputString) {
        if (InputValueType.WILDCARD.equals(inputString)) {
            return true;
        }
        final T evalInstant = parse(inputString);
        return Range.RANGE_CHECK.test(range, evalInstant);
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return name.equals(((GenericRangeEvaluation) other).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    protected abstract T parse(String value);
}
