/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.generate.avroschema;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;

@org.apache.avro.specific.AvroGenerated
public class RoleMap extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5864379815605396620L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"RoleMap\",\"namespace\":\"com.generate.avroschema\",\"fields\":[{\"name\":\"branchId\",\"type\":\"string\"},{\"name\":\"organisationalId\",\"type\":\"long\"},{\"name\":\"roleId\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<RoleMap> ENCODER =
      new BinaryMessageEncoder<RoleMap>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<RoleMap> DECODER =
      new BinaryMessageDecoder<RoleMap>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<RoleMap> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<RoleMap> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<RoleMap> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<RoleMap>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this RoleMap to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a RoleMap from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a RoleMap instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static RoleMap fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated
  public CharSequence branchId;
  @Deprecated
  public long organisationalId;
  @Deprecated
  public CharSequence roleId;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public RoleMap() {}

  /**
   * All-args constructor.
   * @param branchId The new value for branchId
   * @param organisationalId The new value for organisationalId
   * @param roleId The new value for roleId
   */
  public RoleMap(CharSequence branchId, Long organisationalId, CharSequence roleId) {
    this.branchId = branchId;
    this.organisationalId = organisationalId;
    this.roleId = roleId;
  }

  public SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public Object get(int field$) {
    switch (field$) {
    case 0: return branchId;
    case 1: return organisationalId;
    case 2: return roleId;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, Object value$) {
    switch (field$) {
    case 0: branchId = (CharSequence)value$; break;
    case 1: organisationalId = (Long)value$; break;
    case 2: roleId = (CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'branchId' field.
   * @return The value of the 'branchId' field.
   */
  public CharSequence getBranchId() {
    return branchId;
  }


  /**
   * Sets the value of the 'branchId' field.
   * @param value the value to set.
   */
  public void setBranchId(CharSequence value) {
    this.branchId = value;
  }

  /**
   * Gets the value of the 'organisationalId' field.
   * @return The value of the 'organisationalId' field.
   */
  public long getOrganisationalId() {
    return organisationalId;
  }


  /**
   * Sets the value of the 'organisationalId' field.
   * @param value the value to set.
   */
  public void setOrganisationalId(long value) {
    this.organisationalId = value;
  }

  /**
   * Gets the value of the 'roleId' field.
   * @return The value of the 'roleId' field.
   */
  public CharSequence getRoleId() {
    return roleId;
  }


  /**
   * Sets the value of the 'roleId' field.
   * @param value the value to set.
   */
  public void setRoleId(CharSequence value) {
    this.roleId = value;
  }

  /**
   * Creates a new RoleMap RecordBuilder.
   * @return A new RoleMap RecordBuilder
   */
  public static com.generate.avroschema.RoleMap.Builder newBuilder() {
    return new com.generate.avroschema.RoleMap.Builder();
  }

  /**
   * Creates a new RoleMap RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new RoleMap RecordBuilder
   */
  public static com.generate.avroschema.RoleMap.Builder newBuilder(com.generate.avroschema.RoleMap.Builder other) {
    if (other == null) {
      return new com.generate.avroschema.RoleMap.Builder();
    } else {
      return new com.generate.avroschema.RoleMap.Builder(other);
    }
  }

  /**
   * Creates a new RoleMap RecordBuilder by copying an existing RoleMap instance.
   * @param other The existing instance to copy.
   * @return A new RoleMap RecordBuilder
   */
  public static com.generate.avroschema.RoleMap.Builder newBuilder(com.generate.avroschema.RoleMap other) {
    if (other == null) {
      return new com.generate.avroschema.RoleMap.Builder();
    } else {
      return new com.generate.avroschema.RoleMap.Builder(other);
    }
  }

  /**
   * RecordBuilder for RoleMap instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<RoleMap>
    implements org.apache.avro.data.RecordBuilder<RoleMap> {

    private CharSequence branchId;
    private long organisationalId;
    private CharSequence roleId;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.generate.avroschema.RoleMap.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.branchId)) {
        this.branchId = data().deepCopy(fields()[0].schema(), other.branchId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.organisationalId)) {
        this.organisationalId = data().deepCopy(fields()[1].schema(), other.organisationalId);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.roleId)) {
        this.roleId = data().deepCopy(fields()[2].schema(), other.roleId);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing RoleMap instance
     * @param other The existing instance to copy.
     */
    private Builder(com.generate.avroschema.RoleMap other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.branchId)) {
        this.branchId = data().deepCopy(fields()[0].schema(), other.branchId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.organisationalId)) {
        this.organisationalId = data().deepCopy(fields()[1].schema(), other.organisationalId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.roleId)) {
        this.roleId = data().deepCopy(fields()[2].schema(), other.roleId);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'branchId' field.
      * @return The value.
      */
    public CharSequence getBranchId() {
      return branchId;
    }


    /**
      * Sets the value of the 'branchId' field.
      * @param value The value of 'branchId'.
      * @return This builder.
      */
    public com.generate.avroschema.RoleMap.Builder setBranchId(CharSequence value) {
      validate(fields()[0], value);
      this.branchId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'branchId' field has been set.
      * @return True if the 'branchId' field has been set, false otherwise.
      */
    public boolean hasBranchId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'branchId' field.
      * @return This builder.
      */
    public com.generate.avroschema.RoleMap.Builder clearBranchId() {
      branchId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'organisationalId' field.
      * @return The value.
      */
    public long getOrganisationalId() {
      return organisationalId;
    }


    /**
      * Sets the value of the 'organisationalId' field.
      * @param value The value of 'organisationalId'.
      * @return This builder.
      */
    public com.generate.avroschema.RoleMap.Builder setOrganisationalId(long value) {
      validate(fields()[1], value);
      this.organisationalId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'organisationalId' field has been set.
      * @return True if the 'organisationalId' field has been set, false otherwise.
      */
    public boolean hasOrganisationalId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'organisationalId' field.
      * @return This builder.
      */
    public com.generate.avroschema.RoleMap.Builder clearOrganisationalId() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'roleId' field.
      * @return The value.
      */
    public CharSequence getRoleId() {
      return roleId;
    }


    /**
      * Sets the value of the 'roleId' field.
      * @param value The value of 'roleId'.
      * @return This builder.
      */
    public com.generate.avroschema.RoleMap.Builder setRoleId(CharSequence value) {
      validate(fields()[2], value);
      this.roleId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'roleId' field has been set.
      * @return True if the 'roleId' field has been set, false otherwise.
      */
    public boolean hasRoleId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'roleId' field.
      * @return This builder.
      */
    public com.generate.avroschema.RoleMap.Builder clearRoleId() {
      roleId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public RoleMap build() {
      try {
        RoleMap record = new RoleMap();
        record.branchId = fieldSetFlags()[0] ? this.branchId : (CharSequence) defaultValue(fields()[0]);
        record.organisationalId = fieldSetFlags()[1] ? this.organisationalId : (Long) defaultValue(fields()[1]);
        record.roleId = fieldSetFlags()[2] ? this.roleId : (CharSequence) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<RoleMap>
    WRITER$ = (org.apache.avro.io.DatumWriter<RoleMap>)MODEL$.createDatumWriter(SCHEMA$);

  @Override
  public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<RoleMap>
    READER$ = (org.apache.avro.io.DatumReader<RoleMap>)MODEL$.createDatumReader(SCHEMA$);

  @Override
  public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override
  protected boolean hasCustomCoders() { return true; }

  @Override
  public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.branchId);

    out.writeLong(this.organisationalId);

    out.writeString(this.roleId);

  }

  @Override
  public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.branchId = in.readString(this.branchId instanceof Utf8 ? (Utf8)this.branchId : null);

      this.organisationalId = in.readLong();

      this.roleId = in.readString(this.roleId instanceof Utf8 ? (Utf8)this.roleId : null);

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.branchId = in.readString(this.branchId instanceof Utf8 ? (Utf8)this.branchId : null);
          break;

        case 1:
          this.organisationalId = in.readLong();
          break;

        case 2:
          this.roleId = in.readString(this.roleId instanceof Utf8 ? (Utf8)this.roleId : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










