package com.my_example.service;

/**
 * Single Responsibility: Provides interpretations for numerology numbers
 * Open/Closed Principle: Can be extended with new interpretations without modifying existing code
 */
public class NumerologyInterpretationService {
    
    public String getCareerInterpretation(int lifePathNumber) {
        switch (lifePathNumber) {
            case 1: return "Natural leader, entrepreneur, independent worker. Best in management, business, or creative fields.";
            case 2: return "Cooperative, diplomatic, team player. Excels in partnerships, counseling, or service-oriented careers.";
            case 3: return "Creative, expressive, communicative. Thrives in arts, writing, teaching, or entertainment.";
            case 4: return "Practical, organized, systematic. Best in engineering, accounting, or structured environments.";
            case 5: return "Adventurous, versatile, freedom-loving. Suited for sales, travel, marketing, or dynamic fields.";
            case 6: return "Nurturing, responsible, service-oriented. Ideal for healthcare, teaching, or caregiving roles.";
            case 7: return "Analytical, spiritual, introspective. Best in research, science, philosophy, or spiritual fields.";
            case 8: return "Ambitious, material success, leadership. Excels in business, finance, or executive positions.";
            case 9: return "Humanitarian, compassionate, idealistic. Best in healing, teaching, or humanitarian work.";
            case 11: return "Intuitive, inspirational, visionary. Ideal for counseling, teaching, or spiritual guidance.";
            case 22: return "Master builder, practical visionary. Best in large-scale projects, architecture, or organization.";
            case 33: return "Master teacher, compassionate healer. Ideal for teaching, healing, or humanitarian leadership.";
            default: return "Your life path number indicates unique career potential.";
        }
    }
    
    public String getRelationshipInterpretation(int soulNumber) {
        switch (soulNumber) {
            case 1: return "Independent partner, needs freedom and respect. Values equality in relationships.";
            case 2: return "Sensitive, romantic, seeks harmony. Values partnership and emotional connection.";
            case 3: return "Expressive, fun-loving, communicative. Brings joy and creativity to relationships.";
            case 4: return "Stable, loyal, committed. Values security and long-term commitment.";
            case 5: return "Adventurous, freedom-loving, needs space. Values excitement and variety.";
            case 6: return "Nurturing, caring, family-oriented. Values harmony and emotional support.";
            case 7: return "Introspective, spiritual, selective. Values deep connection and understanding.";
            case 8: return "Ambitious, material-focused, powerful. Values success and achievement in partnerships.";
            case 9: return "Compassionate, idealistic, giving. Values unconditional love and service.";
            case 11: return "Intuitive, idealistic, sensitive. Seeks deep spiritual connection.";
            case 22: return "Practical, supportive, master builder. Values stability and growth together.";
            case 33: return "Compassionate, healing, nurturing. Values unconditional love and service.";
            default: return "Your soul number indicates unique relationship characteristics.";
        }
    }
    
    public String getHealthInterpretation(int lifePathNumber) {
        switch (lifePathNumber) {
            case 1: return "Strong constitution, but watch for stress-related issues. Regular exercise and stress management important.";
            case 2: return "Sensitive system, prone to stress. Focus on emotional balance and gentle exercise.";
            case 3: return "Vibrant energy, but watch for overindulgence. Balance creativity with physical activity.";
            case 4: return "Sturdy build, but prone to rigidity. Regular movement and flexibility exercises recommended.";
            case 5: return "Active lifestyle, but watch for restlessness. Balance activity with rest and routine.";
            case 6: return "Strong nurturing energy, but may neglect own needs. Self-care and boundaries important.";
            case 7: return "Sensitive system, needs quiet and rest. Focus on mental health and spiritual practices.";
            case 8: return "Strong energy, but watch for work-related stress. Balance ambition with relaxation.";
            case 9: return "Compassionate energy, but may overextend. Focus on self-care and boundaries.";
            case 11: return "Highly sensitive, needs protection from stress. Meditation and spiritual practices essential.";
            case 22: return "Strong but needs balance. Focus on maintaining physical and mental equilibrium.";
            case 33: return "Healing energy, but needs self-care. Balance giving with receiving.";
            default: return "Maintain balance in all aspects of health.";
        }
    }
    
    public String getMoneyInterpretation(int destinyNumber) {
        switch (destinyNumber) {
            case 1: return "Natural ability to earn through leadership and innovation. Financial independence achievable.";
            case 2: return "Money through partnerships and cooperation. Steady, reliable income preferred.";
            case 3: return "Earning through creativity and communication. Multiple income streams possible.";
            case 4: return "Stable, systematic approach to money. Long-term financial planning important.";
            case 5: return "Variable income, multiple sources. Financial freedom through versatility.";
            case 6: return "Money through service and caregiving. Stable income from helping others.";
            case 7: return "Earning through knowledge and expertise. Research and analysis lead to financial success.";
            case 8: return "Strong financial potential, material success. Business and investment opportunities.";
            case 9: return "Money through humanitarian work. Financial success through giving and service.";
            case 11: return "Intuitive approach to finances. Success through inspiration and guidance.";
            case 22: return "Master builder of wealth. Large-scale financial projects and investments.";
            case 33: return "Financial success through teaching and healing. Money follows service.";
            default: return "Your destiny number indicates unique financial characteristics.";
        }
    }
    
    public String getCharacteristicsInterpretation(int lifePathNumber) {
        switch (lifePathNumber) {
            case 1: return "Leadership, independence, originality, determination, courage, innovation.";
            case 2: return "Cooperation, diplomacy, sensitivity, patience, harmony, partnership.";
            case 3: return "Creativity, expression, communication, optimism, joy, enthusiasm.";
            case 4: return "Stability, organization, practicality, discipline, reliability, hard work.";
            case 5: return "Freedom, adventure, versatility, curiosity, change, excitement.";
            case 6: return "Nurturing, responsibility, service, harmony, love, care.";
            case 7: return "Analysis, spirituality, introspection, wisdom, seeking, understanding.";
            case 8: return "Ambition, material success, power, organization, achievement, authority.";
            case 9: return "Humanitarianism, compassion, idealism, generosity, wisdom, completion.";
            case 11: return "Intuition, inspiration, idealism, sensitivity, spiritual awareness, enlightenment.";
            case 22: return "Master builder, practical vision, organization, large-scale thinking, manifestation.";
            case 33: return "Master teacher, compassion, healing, unconditional love, spiritual service.";
            default: return "Unique characteristics based on your life path number.";
        }
    }
}



