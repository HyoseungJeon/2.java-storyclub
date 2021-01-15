package io.namoosori.travelclub.step3.service.dto;

import io.namoosori.travelclub.step1.entity.board.Posting;
import io.namoosori.travelclub.step1.entity.board.SocialBoard;
import io.namoosori.travelclub.util.DateUtil;

public class PostingDto {
    private String usid;
    private String title;
    private String writerEmail;
    private String contents;
    private String writtenDate;
    private int readCount;

    public PostingDto() {

    }

    public PostingDto(String title, String writerEmail, String contents) {
        this();
        this.title = title;
        this.writerEmail = writerEmail;
        this.contents = contents;
        this.writtenDate = DateUtil.today();
    }

    public PostingDto(Posting posting){

        this.usid = posting.getUsid();
        this.title = posting.getTitle();
        this.writerEmail = posting.getWriterEmail();
        this.contents = posting.getContents();
        this.writtenDate = posting.getWrittenDate();
        this.readCount = posting.getReadCount();
    }

    public Posting toPostingIn(SocialBoard board){
        Posting posting = new Posting(board, title, writerEmail, contents);
        posting.setWrittenDate(writtenDate);
        posting.setReadCount(readCount);
        return posting;
    }

    public Posting toPostingIn(String postingId, String boardId){
        Posting posting = new Posting(postingId, boardId, writerEmail, title, contents);
        posting.setWrittenDate(writtenDate);
        posting.setReadCount(readCount);
        return posting;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PostingDto{");
        sb.append("usid='").append(usid).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", writerEmail='").append(writerEmail).append('\'');
        sb.append(", readCount='").append(contents).append('\'');
        sb.append(", writtenDate='").append(writtenDate).append('\'');
        sb.append(", contents=").append(readCount);
        sb.append('}');
        return sb.toString();
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriterEmail() {
        return writerEmail;
    }

    public void setWriterEmail(String writerEmail) {
        this.writerEmail = writerEmail;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWrittenDate() {
        return writtenDate;
    }

    public void setWrittenDate(String writtenDate) {
        this.writtenDate = writtenDate;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }
}
