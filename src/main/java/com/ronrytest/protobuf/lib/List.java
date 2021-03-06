// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: lib/list.proto

package com.ronrytest.protobuf.lib;

public final class List {
  private List() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ListEntityOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // repeated bytes value = 1;
    /**
     * <code>repeated bytes value = 1;</code>
     */
    java.util.List<com.google.protobuf.ByteString> getValueList();
    /**
     * <code>repeated bytes value = 1;</code>
     */
    int getValueCount();
    /**
     * <code>repeated bytes value = 1;</code>
     */
    com.google.protobuf.ByteString getValue(int index);
  }
  /**
   * Protobuf type {@code com.ronrytest.protobuffer.lib.ListEntity}
   */
  public static final class ListEntity extends
      com.google.protobuf.GeneratedMessage
      implements ListEntityOrBuilder {
    // Use ListEntity.newBuilder() to construct.
    private ListEntity(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ListEntity(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ListEntity defaultInstance;
    public static ListEntity getDefaultInstance() {
      return defaultInstance;
    }

    public ListEntity getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ListEntity(
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
            case 10: {
              if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
                value_ = new java.util.ArrayList<com.google.protobuf.ByteString>();
                mutable_bitField0_ |= 0x00000001;
              }
              value_.add(input.readBytes());
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
        if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
          value_ = java.util.Collections.unmodifiableList(value_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.ronrytest.protobuf.lib.List.internal_static_com_ronrytest_protobuffer_lib_ListEntity_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.ronrytest.protobuf.lib.List.internal_static_com_ronrytest_protobuffer_lib_ListEntity_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.ronrytest.protobuf.lib.List.ListEntity.class, com.ronrytest.protobuf.lib.List.ListEntity.Builder.class);
    }

    public static com.google.protobuf.Parser<ListEntity> PARSER =
        new com.google.protobuf.AbstractParser<ListEntity>() {
      public ListEntity parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ListEntity(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ListEntity> getParserForType() {
      return PARSER;
    }

    // repeated bytes value = 1;
    public static final int VALUE_FIELD_NUMBER = 1;
    private java.util.List<com.google.protobuf.ByteString> value_;
    /**
     * <code>repeated bytes value = 1;</code>
     */
    public java.util.List<com.google.protobuf.ByteString>
        getValueList() {
      return value_;
    }
    /**
     * <code>repeated bytes value = 1;</code>
     */
    public int getValueCount() {
      return value_.size();
    }
    /**
     * <code>repeated bytes value = 1;</code>
     */
    public com.google.protobuf.ByteString getValue(int index) {
      return value_.get(index);
    }

    private void initFields() {
      value_ = java.util.Collections.emptyList();
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
      for (int i = 0; i < value_.size(); i++) {
        output.writeBytes(1, value_.get(i));
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      {
        int dataSize = 0;
        for (int i = 0; i < value_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeBytesSizeNoTag(value_.get(i));
        }
        size += dataSize;
        size += 1 * getValueList().size();
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

    public static com.ronrytest.protobuf.lib.List.ListEntity parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.ronrytest.protobuf.lib.List.ListEntity parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.ronrytest.protobuf.lib.List.ListEntity parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.ronrytest.protobuf.lib.List.ListEntity parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.ronrytest.protobuf.lib.List.ListEntity parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.ronrytest.protobuf.lib.List.ListEntity parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.ronrytest.protobuf.lib.List.ListEntity parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.ronrytest.protobuf.lib.List.ListEntity parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.ronrytest.protobuf.lib.List.ListEntity parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.ronrytest.protobuf.lib.List.ListEntity parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.ronrytest.protobuf.lib.List.ListEntity prototype) {
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
     * Protobuf type {@code com.ronrytest.protobuffer.lib.ListEntity}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.ronrytest.protobuf.lib.List.ListEntityOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.ronrytest.protobuf.lib.List.internal_static_com_ronrytest_protobuffer_lib_ListEntity_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.ronrytest.protobuf.lib.List.internal_static_com_ronrytest_protobuffer_lib_ListEntity_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.ronrytest.protobuf.lib.List.ListEntity.class, com.ronrytest.protobuf.lib.List.ListEntity.Builder.class);
      }

      // Construct using com.ronrytest.protobuffer.lib.List.ListEntity.newBuilder()
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
        value_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.ronrytest.protobuf.lib.List.internal_static_com_ronrytest_protobuffer_lib_ListEntity_descriptor;
      }

      public com.ronrytest.protobuf.lib.List.ListEntity getDefaultInstanceForType() {
        return com.ronrytest.protobuf.lib.List.ListEntity.getDefaultInstance();
      }

      public com.ronrytest.protobuf.lib.List.ListEntity build() {
        com.ronrytest.protobuf.lib.List.ListEntity result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.ronrytest.protobuf.lib.List.ListEntity buildPartial() {
        com.ronrytest.protobuf.lib.List.ListEntity result = new com.ronrytest.protobuf.lib.List.ListEntity(this);
        int from_bitField0_ = bitField0_;
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          value_ = java.util.Collections.unmodifiableList(value_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.value_ = value_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.ronrytest.protobuf.lib.List.ListEntity) {
          return mergeFrom((com.ronrytest.protobuf.lib.List.ListEntity)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.ronrytest.protobuf.lib.List.ListEntity other) {
        if (other == com.ronrytest.protobuf.lib.List.ListEntity.getDefaultInstance()) return this;
        if (!other.value_.isEmpty()) {
          if (value_.isEmpty()) {
            value_ = other.value_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureValueIsMutable();
            value_.addAll(other.value_);
          }
          onChanged();
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
        com.ronrytest.protobuf.lib.List.ListEntity parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.ronrytest.protobuf.lib.List.ListEntity) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // repeated bytes value = 1;
      private java.util.List<com.google.protobuf.ByteString> value_ = java.util.Collections.emptyList();
      private void ensureValueIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          value_ = new java.util.ArrayList<com.google.protobuf.ByteString>(value_);
          bitField0_ |= 0x00000001;
         }
      }
      /**
       * <code>repeated bytes value = 1;</code>
       */
      public java.util.List<com.google.protobuf.ByteString>
          getValueList() {
        return java.util.Collections.unmodifiableList(value_);
      }
      /**
       * <code>repeated bytes value = 1;</code>
       */
      public int getValueCount() {
        return value_.size();
      }
      /**
       * <code>repeated bytes value = 1;</code>
       */
      public com.google.protobuf.ByteString getValue(int index) {
        return value_.get(index);
      }
      /**
       * <code>repeated bytes value = 1;</code>
       */
      public Builder setValue(
          int index, com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureValueIsMutable();
        value_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated bytes value = 1;</code>
       */
      public Builder addValue(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureValueIsMutable();
        value_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated bytes value = 1;</code>
       */
      public Builder addAllValue(
          java.lang.Iterable<? extends com.google.protobuf.ByteString> values) {
        ensureValueIsMutable();
        super.addAll(values, value_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated bytes value = 1;</code>
       */
      public Builder clearValue() {
        value_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.ronrytest.protobuffer.lib.ListEntity)
    }

    static {
      defaultInstance = new ListEntity(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.ronrytest.protobuffer.lib.ListEntity)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_ronrytest_protobuffer_lib_ListEntity_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_ronrytest_protobuffer_lib_ListEntity_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016lib/list.proto\022\035com.ronrytest.protobuf" +
      "fer.lib\"\033\n\nListEntity\022\r\n\005value\030\001 \003(\014"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_com_ronrytest_protobuffer_lib_ListEntity_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_com_ronrytest_protobuffer_lib_ListEntity_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_ronrytest_protobuffer_lib_ListEntity_descriptor,
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
