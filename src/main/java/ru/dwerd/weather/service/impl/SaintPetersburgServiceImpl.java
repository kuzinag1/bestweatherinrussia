package ru.dwerd.weather.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.dwerd.weather.bot.config.BotState;
import ru.dwerd.weather.feign.WeatherFeignClient;
import ru.dwerd.weather.mapper.UserMapper;
import ru.dwerd.weather.model.Condition;
import ru.dwerd.weather.model.Fact;
import ru.dwerd.weather.model.MemoryUsers;
import ru.dwerd.weather.model.Weather;
import ru.dwerd.weather.service.WeatherSaintPetersburgService;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SaintPetersburgServiceImpl implements WeatherSaintPetersburgService {
    private final WeatherFeignClient weatherFeignClient;
    private final InlineKeyboardMarkup inlineMessageButtons;
    private final  String yandexApiKey;
    private final UserMapper userMapper;
    private final MemoryUsers memoryUsers;
    @Override
    public SendMessage handle(Message message) {
        memoryUsers.addHistoryUsers(userMapper.toUser(message));
        final long chatId = message.getChatId();
        Weather weather = weatherFeignClient.getWeather(yandexApiKey,"59.9311","30.3609",true);
        String meaasageWeather = getWeatherSaintPersburgNowFromYandexApiMessage(weather.getFact(),weather);
        SendMessage replyToUser = new SendMessage(String.valueOf(chatId),meaasageWeather);
        replyToUser.setReplyMarkup(inlineMessageButtons);
        return replyToUser;
    }
    @Override
    public SendMessage handle(final long chatId) {
        Weather weather = weatherFeignClient.getWeather(yandexApiKey,"59.9311","30.3609",true);
        String meaasageWeather = getWeatherSaintPersburgNowFromYandexApiMessage(weather.getFact(),weather);
        SendMessage sendMessage =new SendMessage(String.valueOf(chatId),meaasageWeather);
        sendMessage.setReplyMarkup(inlineMessageButtons);
        return sendMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SAINT_PETERSBURG;
    }
    private String getWeatherSaintPersburgNowFromYandexApiMessage(Fact fact, Weather weather) {
        StringBuilder weatherStringBuilder = new StringBuilder();
        weatherStringBuilder.append("???????????? ?? ??????????-???????????????????? ????????????:\n");
        weatherStringBuilder.append("??????????????????????: ").append(fact.getTemp()).append("??C\n");
        weatherStringBuilder.append("?????????????????? ??????????????????????: ").append(fact.getFeelsLike()).append("??C\n");
        Condition condition = Condition.valueOf(weather.getFact().getCondition().toUpperCase(Locale.ROOT));
        weatherStringBuilder.append("???????????????? ????????????????: ").append(condition.getCondition()).append("\n");
        weatherStringBuilder.append("???????????????? (?? ???? ????. ????.): ").append(fact.getPressureMm()).append("\n");
        weatherStringBuilder.append("?????????????????? ??????????????: ").append(fact.getHumidity()).append("%\n");
        weatherStringBuilder.append("???????? ????????: ").append(weather.getForecastsList().get(0).getMoonCodeInText()).append("\n");
        weatherStringBuilder.append("???????????? ????????????: ").append(weather.getForecastsList().get(0).getSunrise()).append("\n");
        weatherStringBuilder.append("?????????? ????????????: ").append(weather.getForecastsList().get(0).getSunset());
        return  weatherStringBuilder.toString();
    }
}
