/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/googleapis/google-api-java-client-services/
 * Modify at your own risk.
 */

package kr.co.cc.stat.service;
//package com.google.api.services.youtubeAnalytics.v2.model;

/**
 * A group item.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the YouTube Analytics API. For a detailed explanation
 * see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class GroupItem extends com.google.api.client.json.GenericJson {

  /**
   * Apiary error details
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Errors errors;

  /**
   * The Etag of this resource.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String etag;

  /**
   * The ID that YouTube uses to uniquely identify the group that contains the item.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String groupId;

  /**
   * The ID that YouTube uses to uniquely identify the `channel`, `video`, `playlist`, or `asset`
   * resource that is included in the group. Note that this ID refers specifically to the inclusion
   * of that resource in a particular group and is different than the channel ID, video ID, playlist
   * ID, or asset ID that uniquely identifies the resource itself. The `resource.id` property's
   * value specifies the unique channel, video, playlist, or asset ID.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String id;

  /**
   * Identifies the API resource's type. The value will be `youtube#groupItem`.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String kind;

  /**
   * The `resource` object contains information that identifies the item being added to the group.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private GroupItemResource resource;

  /**
   * Apiary error details
   * @return value or {@code null} for none
   */
  public Errors getErrors() {
    return errors;
  }

  /**
   * Apiary error details
   * @param errors errors or {@code null} for none
   */
  public GroupItem setErrors(Errors errors) {
    this.errors = errors;
    return this;
  }

  /**
   * The Etag of this resource.
   * @return value or {@code null} for none
   */
  public java.lang.String getEtag() {
    return etag;
  }

  /**
   * The Etag of this resource.
   * @param etag etag or {@code null} for none
   */
  public GroupItem setEtag(java.lang.String etag) {
    this.etag = etag;
    return this;
  }

  /**
   * The ID that YouTube uses to uniquely identify the group that contains the item.
   * @return value or {@code null} for none
   */
  public java.lang.String getGroupId() {
    return groupId;
  }

  /**
   * The ID that YouTube uses to uniquely identify the group that contains the item.
   * @param groupId groupId or {@code null} for none
   */
  public GroupItem setGroupId(java.lang.String groupId) {
    this.groupId = groupId;
    return this;
  }

  /**
   * The ID that YouTube uses to uniquely identify the `channel`, `video`, `playlist`, or `asset`
   * resource that is included in the group. Note that this ID refers specifically to the inclusion
   * of that resource in a particular group and is different than the channel ID, video ID, playlist
   * ID, or asset ID that uniquely identifies the resource itself. The `resource.id` property's
   * value specifies the unique channel, video, playlist, or asset ID.
   * @return value or {@code null} for none
   */
  public java.lang.String getId() {
    return id;
  }

  /**
   * The ID that YouTube uses to uniquely identify the `channel`, `video`, `playlist`, or `asset`
   * resource that is included in the group. Note that this ID refers specifically to the inclusion
   * of that resource in a particular group and is different than the channel ID, video ID, playlist
   * ID, or asset ID that uniquely identifies the resource itself. The `resource.id` property's
   * value specifies the unique channel, video, playlist, or asset ID.
   * @param id id or {@code null} for none
   */
  public GroupItem setId(java.lang.String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifies the API resource's type. The value will be `youtube#groupItem`.
   * @return value or {@code null} for none
   */
  public java.lang.String getKind() {
    return kind;
  }

  /**
   * Identifies the API resource's type. The value will be `youtube#groupItem`.
   * @param kind kind or {@code null} for none
   */
  public GroupItem setKind(java.lang.String kind) {
    this.kind = kind;
    return this;
  }

  /**
   * The `resource` object contains information that identifies the item being added to the group.
   * @return value or {@code null} for none
   */
  public GroupItemResource getResource() {
    return resource;
  }

  /**
   * The `resource` object contains information that identifies the item being added to the group.
   * @param resource resource or {@code null} for none
   */
  public GroupItem setResource(GroupItemResource resource) {
    this.resource = resource;
    return this;
  }

  @Override
  public GroupItem set(String fieldName, Object value) {
    return (GroupItem) super.set(fieldName, value);
  }

  @Override
  public GroupItem clone() {
    return (GroupItem) super.clone();
  }

}