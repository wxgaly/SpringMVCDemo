package pojo;

/**
 * pojo.Message
 *
 * @author Created by WXG on 2018/5/15 015 14:00.
 * @version V1.0
 */

public class Message {

    private String sourceId;
    private String destinationId;
    private String message;
    private String type;
    private String imageUrl;

    public Message() {
    }

    public Message(String sourceId, String destinationId, String message) {
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.message = message;
    }

    public Message(String sourceId, String destinationId, String message, String type, String imageUrl) {
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.message = message;
        this.type = type;
        this.imageUrl = imageUrl;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
