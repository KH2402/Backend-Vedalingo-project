package com.vedalingo.controllers;

import com.vedalingo.dtos.TranslationRequestDto;
import com.vedalingo.models.translation.ChatCompletionRequest;
import com.vedalingo.models.translation.ChatCompletionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class TranslationController {

    private static final Logger logger = LoggerFactory.getLogger(TranslationController.class);

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/translate")
    public ResponseEntity<String> getOpenaiResponse(@RequestBody TranslationRequestDto translationRequestDto) {
        try {
            String prompt = translationRequestDto.getText() + " translate into " + translationRequestDto.getTargetLanguage() + " only translated text shows as output";
            logger.info("Prompt: {}", prompt);

            ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest("gpt-3.5-turbo", prompt);
            logger.info("Request: {}", chatCompletionRequest.toString());

            ChatCompletionResponse response = restTemplate.postForObject("https://api.openai.com/v1/chat/completions", chatCompletionRequest, ChatCompletionResponse.class);
            logger.info("Response: {}", response.toString());

            String translatedText = response.getChoices().get(0).getMessage().getContent();
            return ResponseEntity.ok(translatedText);
        } catch (HttpClientErrorException.Unauthorized unauthorized) {
            logger.error("Unauthorized access to OpenAI API. Check your API key.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access to OpenAI API.");
        } catch (Exception e) {
            logger.error("Error while processing OpenAI API request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while processing OpenAI API request.");
        }
    }
}
