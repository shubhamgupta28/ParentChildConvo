package usc.com.uscmaps.example1.shubham.parentchildconvo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shubham on 3/22/17.
 */

class MessageData {
    private String is_starred;
    private String subject;
    private List<MessagesThread> messages = new ArrayList<>();

    String getIs_starred() {
        return is_starred;
    }

    void setIs_starred(String is_starred) {
        this.is_starred = is_starred;
    }

    String getSubject() {
        return subject;
    }

    void setSubject(String subject) {
        this.subject = subject;
    }

    List<MessagesThread> getMessages() {
        return messages;
    }

    void setMessages(List<MessagesThread> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "MessageData{" +
                "is_starred='" + is_starred + '\'' +
                ", subject='" + subject + '\'' +
                ", messages=" + messages +
                '}';
    }
}

class MessagesThread {
    private String sender_id;
    private String sender_name;
    private String sender_image;
    private String sender_email;
    private String text;
    private String timestamp;
    private List<Attachment> attachments = new ArrayList<>();
    private List<SentTo> sent_to = new ArrayList<>();

    @Override
    public String toString() {
        return "MessagesThread{" +
                "sender_id='" + sender_id + '\'' +
                ", sender_name='" + sender_name + '\'' +
                ", sender_image='" + sender_image + '\'' +
                ", sender_email='" + sender_email + '\'' +
                ", text='" + text + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", attachments=" + attachments +
                ", sent_to=" + sent_to +
                '}';
    }

    public String getSender_id() {
        return sender_id;
    }

    void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    String getSender_name() {
        return sender_name;
    }

    void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    String getSender_image() {
        return sender_image;
    }

    void setSender_image(String sender_image) {
        this.sender_image = sender_image;
    }

    String getSender_email() {
        return sender_email;
    }

    void setSender_email(String sender_email) {
        this.sender_email = sender_email;
    }

    String getText() {
        return text;
    }

    void setText(String text) {
        this.text = text;
    }

    String getTimestamp() {
        return timestamp;
    }

    void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    List<Attachment> getAttachments() {
        return attachments;
    }

    void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    List<SentTo> getSent_to() {
        return sent_to;
    }

    void setSent_to(List<SentTo> sent_to) {
        this.sent_to = sent_to;
    }
}

class Attachment {
    private String type;
    private String name;
    private String download_url;
    private String size;

    @Override
    public String toString() {
        return "Attachment{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", download_url='" + download_url + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

class SentTo {
    private String id;
    private String name;
    private String email;
    private String image_url;

    @Override
    public String toString() {
        return "SentTo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

     void setEmail(String email) {
        this.email = email;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

