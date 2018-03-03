package io.christoph.learn.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.christoph.learn.messenger.database.DatabaseClass;
import io.christoph.learn.messenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1, "Hello world", "christoph"));
		messages.put(2L, new Message(2, "Hello Jersey", "christoph"));
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		return messages.values().stream()
					.filter(predicated -> predicated.getCreated().getYear() == year)
					.collect(Collectors.toList());
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		List<Message> list = new ArrayList<>(messages.values());
		//if (start + size > messages.size()) return list;
		return list.subList(start, size);
	}
	
	public Message getMessage(long id)
	{
		return messages.get(id);
	}
	
	public Message addMessage(Message message)
	{
		message.setId(messages.size() +1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message)
	{
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return messages.get(message.getId());
	}
	
	public Message removeMessage(long id)
	{
		return messages.remove(id);
	}
	
}
