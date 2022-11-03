package org.engcia.Dto;

public class ResponseDto {

    public String Question;
    public boolean isConclusion;
    public String Conclusion;
    public String How;

    public ResponseDto(String question){
        Question = question;
        isConclusion = false;
    }

    public ResponseDto(String conclusion, String how){
        Conclusion = conclusion;
        How = how;
        isConclusion = true;
    }

}
