@org.hibernate.annotations.GenericGenerator(name = "ID_GENERATOR", strategy = "enhanced-sequence", parameters = {
        @org.hibernate.annotations.Parameter(name = "sequence_name", value = "caveatemptor_sequence"),
        @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000") })
package com.datvutech.data;
