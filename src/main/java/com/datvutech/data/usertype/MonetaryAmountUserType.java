package com.datvutech.data.usertype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.usertype.DynamicParameterizedType;

/* this is Hibernate specs,
 the standardized JPA converters don’t support transformation of values from/to multiple columns */
public class MonetaryAmountUserType
        implements CompositeUserType, DynamicParameterizedType {

    private Currency convertTo;

    @Override
    public void setParameterValues(Properties params) {

        /* access some dynamic parameters, for config */
        // ParameterType paramType = (ParameterType) params.get(PARAMETER_TYPE);
        // String[] columns = parameterType.getColumns();
        // String table = parameterType.getTable();
        // Annotation[] annotations = parameterType.getAnnotationsMethod();
        String convertToParam = params.getProperty("convertTo");
        this.convertTo = Currency.getInstance(
                convertToParam != null ? convertToParam : "USD");
    }

    /*
     * providing the details of the MonetaryAmount properties so Hibernate can
     * integrate the * class with the query engine
     */
    @Override
    public String[] getPropertyNames() {
        return new String[] { "value", "currency" };
    }

    /*
     * providing the details of the MonetaryAmount properties so Hibernate can
     * integrate the * class with the query engine
     */
    @Override
    public Type[] getPropertyTypes() {
        return new Type[] { StandardBasicTypes.BIG_DECIMAL, StandardBasicTypes.CURRENCY };
    }

    /*
     * providing the details of the MonetaryAmount properties so Hibernate can
     * integrate the * class with the query engine
     */
    @Override
    public Object getPropertyValue(Object component, int property) throws HibernateException {
        MonetaryAmount monetaryAmount = (MonetaryAmount) component;
        if (property == 0) {
            return monetaryAmount.getValue();
        } else {
            return monetaryAmount.getCurrency();
        }
    }

    /*
     * providing the details of the MonetaryAmount properties so Hibernate can
     * integrate the * class with the query engine
     */
    @Override
    public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
        throw new UnsupportedOperationException("MonetaryAmount is immutable");
    }

    @Override
    public Class<?> returnedClass() {
        /* Adapt class */
        return MonetaryAmount.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        /*
         * Hibernate uses value equality to determine whether the value was changed and
         * the
         * database needs to be updated.
         */
        return x == y || (!(x == null || y == null) && x.equals(y));
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
            throws HibernateException, SQLException {
        /*
         * This is called to read the ResultSet when a MonetaryAmount value has to be
         * retrieved from the database. You take the amount and currency values as
         * given in the query result and create a new instance of MonetaryAmount.
         */
        BigDecimal amount = rs.getBigDecimal(names[0]);
        if (rs.wasNull())
            return null;
        Currency currency = Currency.getInstance(rs.getString(names[1]));
        return new MonetaryAmount(amount, currency);
    }

    @Override
    public void nullSafeSet(PreparedStatement statement, Object value, int index,
            SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
        /*
         * This is called when a MonetaryAmount value has to be stored in the database.
         * You convert the value to the target currency and then set the amount and
         * currency on the provided PreparedStatement (unless MonetaryAmount was null,
         * in which case you call setNull() to prepare the statement).
         */
        if (value == null) {
            statement.setNull(
                    index,
                    StandardBasicTypes.BIG_DECIMAL.sqlType());
            statement.setNull(
                    index + 1,
                    StandardBasicTypes.CURRENCY.sqlType());
        } else {
            MonetaryAmount amount = (MonetaryAmount) value;
            MonetaryAmount dbAmount = convert(amount, convertTo);
            statement.setBigDecimal(index, dbAmount.getValue());
            statement.setString(index + 1, convertTo.getCurrencyCode());
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        /* If Hibernate has to make a copy of the value, it calls this method */
        return value;
    }

    @Override
    public boolean isMutable() {
        /* Hibernate can enable some optimizations if it knows that is immutable. */
        return false;
    }

    @Override
    public Serializable disassemble(Object value, SharedSessionContractImplementor session) throws HibernateException {
        /*
         * Hibernate calls disassemble when it stores a value in the global shared
         * second-level cache, need to return Serializable representation
         */
        return value.toString();
    }

    @Override
    public Object assemble(Serializable cached, SharedSessionContractImplementor session, Object owner)
            throws HibernateException {
        /*
         * Hibernate calls this method when it reads the serialized representation from
         * the global shared second-level cache.
         */
        return MonetaryAmount.fromString((String) cached);
    }

    @Override
    public Object replace(Object original, Object target, SharedSessionContractImplementor session, Object owner)
            throws HibernateException {
        /* This is called during EntityManager#merge() operations */
        return original;
    }

    private MonetaryAmount convert(MonetaryAmount amount,
            Currency toCurrency) {
        return new MonetaryAmount(
                amount.getValue().multiply(new BigDecimal(2)),
                toCurrency);
    }
}
