package com.yisoo.wenxin.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class MessageItem implements Serializable {
    @NotNull
    private String role;
    private String content;

    public static Builder builder() {
        return new Builder();
    }

    private MessageItem(Builder builder) {
        setRole(builder.role);
        setContent(builder.content);
    }

    @Getter
    @AllArgsConstructor
    public enum Role {
        USER("user"),
        ASSISTANT("assistant");
        private String name;
    }

    public static final class Builder {
        private @NotNull String role;
        private String content;

        public Builder() {
        }

        public Builder role(@NotNull Role role) {
            this.role = role.getName();
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public MessageItem build() {
            return new MessageItem(this);
        }
    }
}
