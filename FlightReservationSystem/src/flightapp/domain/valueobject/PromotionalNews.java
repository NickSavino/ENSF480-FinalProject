package flightapp.domain.valueobject;

import flightapp.domain.pattern.ContentStrategy;

public class PromotionalNews {

    private String title;
    private ContentStrategy contentStrategy;

    public PromotionalNews(String title, ContentStrategy contentStrategy) {
        this.title = title;
        this.contentStrategy = contentStrategy;
    }

    public String getTitle() {
        return title;
    }

    public String getPromotionalContent() {
        return contentStrategy.generateContent();
    }
    
    public void setContentStrategy(ContentStrategy contentStrategy) {
        this.contentStrategy = contentStrategy;
    }
}
