/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.river.impl.util;

/**
 *
 * @author peter
 */
class ComparableReferrerWrapper<T> extends ReferrerWrapper<T> implements Comparable<Referrer<T>>  {
    private static final long serialVersionUID = 1L;

    ComparableReferrerWrapper(Referrer<T> ref){
        super(ref);
    }
    
   public int compareTo(Referrer<T> o) {
        T t = get();
        T r = o.get();
        if ( t != null && r != null) {
            if ( t instanceof Comparable){
                return ((Comparable) t).compareTo(r);
            }
        }
        if ( hashCode() < o.hashCode()) return -1;
        if ( hashCode() == o.hashCode()) return 0;
        return 1;
    }
    
}
