// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: lib/long.proto

package com.ronrytest.protobuf.lib;

public final class Long {
  private Long() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface LongEntityOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // optional int64 value = 1;
    /**
     * <code>optional int64 value = 1;</code>
     */
    boolean hasValue();
    /**
     * <code>optional int64 value = 1;</code>
     */
    long getValue();
  }
  /**
   * Protobuf type {@code com.ronrytest.protobuffer.lib.LongEntity}
   */
  public static final class LongEntity extends
      com.google.protobuf.GeneratedMessage
      implements LongEntityOrBuilder {
    // Use LongEntity.newBuilder() to construct.
    private LongEntity(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private LongEntity(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final LongEntity defaultInstance;
    public static LongEntity getDefaultInstance() {
      return defaultInstance;
    }

    public LongEntity getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private LongEntity(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              value_ = input.readInt64();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.ronrytest.protobuf.lib.Long.internal_static_com_ronrytest_protobuffer_lib_LongEntity_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.ronrytest.protobuf.lib.Long.internal_static_com_ronrytest_protobuffer_lib_LongEntity_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.ronrytest.protobuf.lib.Long.LongEntity.class, com.ronrytest.protobuf.lib.Long.LongEntity.Builder.class);
    }

    public static com.google.protobuf.Parser<LongEntity> PARSER =
        new com.google.protobuf.AbstractParser<LongEntity>() {
      public LongEntity parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new LongEntity(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<LongEntity> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // optional int64 value = 1;
    public static final int VALUE_FIELD_NUMBER = 1;
    private long value_;
    /**
     * <code>optional int64 value = 1;</code>
     */
    public boolean hasValue() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int64 value = 1;</code>
     */
    public long getValue() {
      return value_;
    }

    private void initFields() {
      value_ = 0L;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt64(1, value_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, value_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.ronrytest.protobuf.lib.Long.LongEntity parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.ronrytest.protobuf.lib.Long.LongEntity parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.ronrytest.protobuf.lib.Long.LongEntity parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.ronrytest.protobuf.lib.Long.LongEntity parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.ronrytest.protobuf.lib.Long.LongEntity parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.ronrytest.protobuf.lib.Long.LongEntity parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.ronrytest.protobuf.lib.Long.LongEntity parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.ronrytest.protobuf.lib.Long.LongEntity parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.ronrytest.protobuf.lib.Long.LongEntity parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.ronrytest.protobuf.lib.Long.LongEntity parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.ronrytest.protobuf.lib.Long.LongEntity prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.ronrytest.protobuffer.lib.LongEntity}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.ronrytest.protobuf.lib.Long.LongEntityOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.ronrytest.protobuf.lib.Long.internal_static_com_ronrytest_protobuffer_lib_LongEntity_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.ronrytest.protobuf.lib.Long.internal_static_com_ronrytest_protobuffer_lib_LongEntity_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.ronrytest.protobuf.lib.Long.LongEntity.class, com.ronrytest.protobuf.lib.Long.LongEntity.Builder.class);
      }

      // Construct using com.ronrytest.protobuffer.lib.Long.LongEntity.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        value_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.ronrytest.protobuf.lib.Long.internal_static_com_ronrytest_protobuffer_lib_LongEntity_descriptor;
      }

      public com.ronrytest.protobuf.lib.Long.LongEntity getDefaultInstanceForType() {
        return com.ronrytest.protobuf.lib.Long.LongEntity.getDefaultInstance();
      }

      public com.ronrytest.protobuf.lib.Long.LongEntity build() {
        com.ronrytest.protobuf.lib.Long.LongEntity result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.ronrytest.protobuf.lib.Long.LongEntity buildPartial() {
        com.ronrytest.protobuf.lib.Long.LongEntity result = new com.ronrytest.protobuf.lib.Long.LongEntity(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.value_ = value_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.ronrytest.protobuf.lib.Long.LongEntity) {
          return mergeFrom((com.ronrytest.protobuf.lib.Long.LongEntity)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.ronrytest.protobuf.lib.Long.LongEntity other) {
        if (other == com.ronrytest.protobuf.lib.Long.LongEntity.getDefaultInstance()) return this;
        if (other.hasValue()) {
          setValue(other.getValue());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.ronrytest.protobuf.lib.Long.LongEntity parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.ronrytest.protobuf.lib.Long.LongEntity) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // optional int64 value = 1;
      private long value_ ;
      /**
       * <code>optional int64 value = 1;</code>
       */
      public boolean hasValue() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int64 value = 1;</code>
       */
      public long getValue() {
        return value_;
      }
      /**
       * <code>optional int64 value = 1;</code>
       */
      public Builder setValue(long value) {
        bitField0_ |= 0x00000001;
        value_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 value = 1;</code>
       */
      public Builder clearValue() {
        bitField0_ = (bitField0_ & ~0x00000001);
        value_ = 0L;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.ronrytest.protobuffer.lib.LongEntity)
    }

    static {
      defaultInstance = new LongEntity(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.ronrytest.protobuffer.lib.LongEntity)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_ronrytest_protobuffer_lib_LongEntity_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_ronrytest_protobuffer_lib_LongEntity_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016lib/long.proto\022\035com.ronrytest.protobuf" +
      "fer.lib\"\033\n\nLongEntity\022\r\n\005value\030\001 \001(\003"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_com_ronrytest_protobuffer_lib_LongEntity_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_com_ronrytest_protobuffer_lib_LongEntity_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_ronrytest_protobuffer_lib_LongEntity_descriptor,
              new java.lang.String[] { "Value", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
